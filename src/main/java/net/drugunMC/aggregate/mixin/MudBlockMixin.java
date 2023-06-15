package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.Optional;


@Mixin(MudBlock.class)
public abstract class MudBlockMixin extends Block implements Fertilizable {



	private static final VoxelShape FULL_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
	private static final VoxelShape SHALLOW_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.6, 1.0);
	private static final VoxelShape SHALLOW_SHAPE_PLANTED = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.75, 1.0);

	private static final VoxelShape BELOW_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.95, 1.0);
	private static final VoxelShape DEEP_SHAPE_PLANTED = VoxelShapes.cuboid(0.05, 0.0, 0.05, 0.95, 0.28, 0.95);
	private static final VoxelShape EMPTY_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);



	public MudBlockMixin(Settings settings) {
		super(settings);
	}


	@Override
	public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
		if( AggregateMain.CONFIG.mudBonemeal() ){
			return world.getBlockState(pos.up()).isAir();
		}
		else{
			return false;
		}


	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state){
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state){
		BlockPos blockPos = pos.up();
		BlockState blockState = Blocks.GRASS.getDefaultState();
		Optional<RegistryEntry.Reference<PlacedFeature>> optional = world.getRegistryManager().get(RegistryKeys.PLACED_FEATURE).getEntry(VegetationPlacedFeatures.GRASS_BONEMEAL);

		label49:
		for(int i = 0; i < 128; ++i) {
			BlockPos blockPos2 = blockPos;

			for(int j = 0; j < i / 16; ++j) {
				blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
				if (!world.getBlockState(blockPos2.down()).isOf(this) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) {
					continue label49;
				}
			}

			BlockState blockState2 = world.getBlockState(blockPos2);
			if (blockState2.isOf(blockState.getBlock()) && random.nextInt(10) == 0) {
				((Fertilizable)blockState.getBlock()).grow(world, random, blockPos2, blockState2);
			}

			if (blockState2.isAir()) {
				RegistryEntry registryEntry;
				if (random.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = ((Biome)world.getBiome(blockPos2).value()).getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {
						continue;
					}

					registryEntry = ((RandomPatchFeatureConfig)((ConfiguredFeature)list.get(0)).config()).feature();
				} else {
					if (!optional.isPresent()) {
						continue;
					}

					registryEntry = (RegistryEntry)optional.get();
				}

				((PlacedFeature)registryEntry.value()).generateUnregistered(world, world.getChunkManager().getChunkGenerator(), random, blockPos2);
			}
		}

	}


	@Override
	public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if( AggregateMain.CONFIG.mudSlow()){
			return VoxelShapes.empty();
		}
		return FULL_SHAPE;
	}

	@Override
	public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
		if( AggregateMain.CONFIG.mudSlow()){
			return VoxelShapes.empty();
		}
		return FULL_SHAPE;
	}


	@Override
	@SuppressWarnings("deprecation")
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if( AggregateMain.CONFIG.armourWeightMud() ){
			if(context instanceof EntityShapeContext){
				if( ((EntityShapeContext) context).getEntity() instanceof PlayerEntity){
					if( ((PlayerEntity) ((EntityShapeContext) context).getEntity()).getArmourWeight() >= AggregateMain.CONFIG.armourWeightHeavy() ){
						return EMPTY_SHAPE;
					}
				}

			}
		}
		if( AggregateMain.CONFIG.mudSlow()){
			if (context instanceof EntityShapeContext entityShapeContext) {
				Entity entity = entityShapeContext.getEntity();
				if (entity != null) {
					if(entity instanceof LivingEntity && !(entity instanceof PassiveEntity) ){
						Block above = world.getBlockState(pos.up()).getBlock();
						Block below = world.getBlockState(pos.down()).getBlock();
						if (below instanceof MudBlock) {
							if (above instanceof MudBlock) {
								return BELOW_SHAPE;
							}
							if (above instanceof AirBlock) {
								return EMPTY_SHAPE;
							}
							return DEEP_SHAPE_PLANTED;
						}
						else{
							if (above instanceof MudBlock) {
								return BELOW_SHAPE;
							}
							if (above instanceof AirBlock) {
								return SHALLOW_SHAPE;
							}
							return SHALLOW_SHAPE_PLANTED;
						}

					}

				}
			}
		}


		return MudBlock.COLLISION_SHAPE;
	}

/*
	@Inject(method = "getCollisionShape(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;", at = @At("HEAD"), cancellable = true)
	public void injectA(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
		if( DrugunsStuff.CONFIG.mudSlow()){
			if (context instanceof EntityShapeContext entityShapeContext) {
				Entity entity = entityShapeContext.getEntity();
				if (entity != null) {
					if(entity instanceof LivingEntity && !(entity instanceof PassiveEntity) ){
						Block above = world.getBlockState(pos.up()).getBlock();
						Block below = world.getBlockState(pos.down()).getBlock();
						if (below instanceof MudBlock) {
							if (above instanceof MudBlock) {
								cir.setReturnValue(BELOW_SHAPE);
								cir.cancel();
							}
							if (above instanceof AirBlock) {
								cir.setReturnValue(EMPTY_SHAPE);
								cir.cancel();
							}
							cir.setReturnValue(DEEP_SHAPE_PLANTED);
							cir.cancel();
						}
						else{
							if (above instanceof MudBlock) {
								cir.setReturnValue(BELOW_SHAPE);
								cir.cancel();
							}
							if (above instanceof AirBlock) {
								cir.setReturnValue(SHALLOW_SHAPE);
								cir.cancel();
							}
							cir.setReturnValue(SHALLOW_SHAPE_PLANTED);
							cir.cancel();
						}

					}

				}
			}
		}



	}
*/

	@Override
	public float getVelocityMultiplier(){
		if( AggregateMain.CONFIG.mudSlow()){
			return 0.7f;
		}
		else {
			return 1f;
		}
	}


	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if( AggregateMain.CONFIG.armourWeightMud() ){
			if( entity instanceof PlayerEntity){
				if( ((PlayerEntity)entity).getArmourWeight() >= AggregateMain.CONFIG.armourWeightHeavy() ){
					entity.slowMovement(state, new Vec3d(0.3, 0.7, 0.3));
					if (! world.getBlockState(pos.up()).isIn(BlockTags.CLIMBABLE)){
						((PlayerEntity) entity).setJumpPreventionTicks(3);
						((PlayerEntity) entity).setJumping(false);
					}
					return;
				}
			}
		}
		if( AggregateMain.CONFIG.mudSlow() ){
			if(entity instanceof PlayerEntity && world.getBlockState(pos.down()).getBlock() instanceof MudBlock){
				if (! world.getBlockState(pos.up()).isIn(BlockTags.CLIMBABLE)){
					((PlayerEntity) entity).setJumpPreventionTicks(3);
					((PlayerEntity) entity).setJumping(false);
				}

				if(world.getBlockState(pos.up()).getBlock() instanceof AirBlock){
					entity.slowMovement(state, new Vec3d(0.5, 0.7, 0.5));
				}
				else{
					entity.slowMovement(state, new Vec3d(0.85, 0.7, 0.85));
				}
			}
		}


	}



}