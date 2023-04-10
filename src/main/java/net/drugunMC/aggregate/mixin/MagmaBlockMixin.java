package net.drugunMC.aggregate.mixin;


import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MagmaBlock.class)
public abstract class MagmaBlockMixin extends Block {

    public MagmaBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance){
        if( !world.isClient && (AggregateMain.CONFIG.armourWeight() || AggregateMain.CONFIG.magmaBreaking()) ){
            if( entity instanceof PlayerEntity){
                if( (AggregateMain.CONFIG.armourWeight() && ((PlayerEntity)entity).getHeavy()) || (AggregateMain.CONFIG.magmaBreaking() && fallDistance > 2) ){
                    ((PlayerEntity) entity).setJumpPreventionTicks(20);

                    world.breakBlock(pos, false);
                    world.setBlockState(pos, Blocks.LAVA.getDefaultState());
                    world.updateNeighbor(pos, Blocks.LAVA, pos);
                    BlockPos pos2;
                    double x = entity.getPos().x % 1;
                    if(x < 0){
                        x = x+1;
                    }
                    double z = entity.getPos().z % 1;
                    if(z < 0){
                        z = z+1;
                    }
                    if(x < 0.5f){
                        pos2 = pos.west();
                        if(z > 0.5f){
                            if(world.getBlockState(pos2.south()).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos2.south(), false);
                                world.setBlockState(pos2.south(), Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos2.south(), Blocks.LAVA, pos2.south());
                            }
                            if(world.getBlockState(pos.south()).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos.south(), false);
                                world.setBlockState(pos.south(), Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos.south(), Blocks.LAVA, pos.south());
                            }
                        }
                        else{
                            if(world.getBlockState(pos2.north()).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos2.north(), false);
                                world.setBlockState(pos2.north(), Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos2.north(), Blocks.LAVA, pos2.north());
                            }
                            if(world.getBlockState(pos.north()).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos.north(), false);
                                world.setBlockState(pos.north(), Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos.north(), Blocks.LAVA, pos.north());
                            }
                        }
                        if(world.getBlockState(pos2).isOf(Blocks.MAGMA_BLOCK)){
                            world.breakBlock(pos2, false);
                            world.setBlockState(pos2, Blocks.LAVA.getDefaultState());
                            world.updateNeighbor(pos2, Blocks.LAVA, pos2);
                        }
                    }
                    else{
                        pos2 = pos.east();
                        if(z > 0.5f){
                            if(world.getBlockState(pos2.south()).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos2.south(), false);
                                world.setBlockState(pos2.south(), Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos2.south(), Blocks.LAVA, pos2.south());
                            }
                            if(world.getBlockState(pos.south()).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos.south(), false);
                                world.setBlockState(pos.south(), Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos.south(), Blocks.LAVA, pos.south());
                            }
                        }
                        else{
                            if(world.getBlockState(pos2.north()).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos2.north(), false);
                                world.setBlockState(pos2.north(), Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos2.north(), Blocks.LAVA, pos2.north());
                            }
                            if(world.getBlockState(pos.north()).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos.north(), false);
                                world.setBlockState(pos.north(), Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos.north(), Blocks.LAVA, pos.north());
                            }
                        }
                        if(world.getBlockState(pos2).isOf(Blocks.MAGMA_BLOCK)){
                            world.breakBlock(pos2, false);
                            world.setBlockState(pos2, Blocks.LAVA.getDefaultState());
                            world.updateNeighbor(pos2, Blocks.LAVA, pos2);
                        }
                    }
                }
            }
        }
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Inject(method = "onSteppedOn", at = @At("HEAD"))
    public void injectedA(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci){
        if( !world.isClient && AggregateMain.CONFIG.armourWeight() ){
            if( entity instanceof PlayerEntity){
                if( ((PlayerEntity)entity).getHeavy() ){
                    if(world.random.nextInt(50) == 0){
                        ((PlayerEntity) entity).setJumpPreventionTicks(20);

                        world.breakBlock(pos, false);
                        world.setBlockState(pos, Blocks.LAVA.getDefaultState());
                        world.updateNeighbor(pos, Blocks.LAVA, pos);
                        BlockPos pos2;
                        double x = entity.getPos().x % 1;
                        if(x < 0){
                            x = x+1;
                        }
                        double z = entity.getPos().z % 1;
                        if(z < 0){
                            z = z+1;
                        }
                        if(x < 0.5f){
                            pos2 = pos.west();
                            if(z > 0.5f){
                                if(world.getBlockState(pos2.south()).isOf(Blocks.MAGMA_BLOCK)){
                                    world.breakBlock(pos2.south(), false);
                                    world.setBlockState(pos2.south(), Blocks.LAVA.getDefaultState());
                                    world.updateNeighbor(pos2.south(), Blocks.LAVA, pos2.south());
                                }
                                if(world.getBlockState(pos.south()).isOf(Blocks.MAGMA_BLOCK)){
                                    world.breakBlock(pos.south(), false);
                                    world.setBlockState(pos.south(), Blocks.LAVA.getDefaultState());
                                    world.updateNeighbor(pos.south(), Blocks.LAVA, pos.south());
                                }
                            }
                            else{
                                if(world.getBlockState(pos2.north()).isOf(Blocks.MAGMA_BLOCK)){
                                    world.breakBlock(pos2.north(), false);
                                    world.setBlockState(pos2.north(), Blocks.LAVA.getDefaultState());
                                    world.updateNeighbor(pos2.north(), Blocks.LAVA, pos2.north());
                                }
                                if(world.getBlockState(pos.north()).isOf(Blocks.MAGMA_BLOCK)){
                                    world.breakBlock(pos.north(), false);
                                    world.setBlockState(pos.north(), Blocks.LAVA.getDefaultState());
                                    world.updateNeighbor(pos.north(), Blocks.LAVA, pos.north());
                                }
                            }
                            if(world.getBlockState(pos2).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos2, false);
                                world.setBlockState(pos2, Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos2, Blocks.LAVA, pos2);
                            }
                        }
                        else{
                            pos2 = pos.east();
                            if(z > 0.5f){
                                if(world.getBlockState(pos2.south()).isOf(Blocks.MAGMA_BLOCK)){
                                    world.breakBlock(pos2.south(), false);
                                    world.setBlockState(pos2.south(), Blocks.LAVA.getDefaultState());
                                    world.updateNeighbor(pos2.south(), Blocks.LAVA, pos2.south());
                                }
                                if(world.getBlockState(pos.south()).isOf(Blocks.MAGMA_BLOCK)){
                                    world.breakBlock(pos.south(), false);
                                    world.setBlockState(pos.south(), Blocks.LAVA.getDefaultState());
                                    world.updateNeighbor(pos.south(), Blocks.LAVA, pos.south());
                                }
                            }
                            else{
                                if(world.getBlockState(pos2.north()).isOf(Blocks.MAGMA_BLOCK)){
                                    world.breakBlock(pos2.north(), false);
                                    world.setBlockState(pos2.north(), Blocks.LAVA.getDefaultState());
                                    world.updateNeighbor(pos2.north(), Blocks.LAVA, pos2.north());
                                }
                                if(world.getBlockState(pos.north()).isOf(Blocks.MAGMA_BLOCK)){
                                    world.breakBlock(pos.north(), false);
                                    world.setBlockState(pos.north(), Blocks.LAVA.getDefaultState());
                                    world.updateNeighbor(pos.north(), Blocks.LAVA, pos.north());
                                }
                            }
                            if(world.getBlockState(pos2).isOf(Blocks.MAGMA_BLOCK)){
                                world.breakBlock(pos2, false);
                                world.setBlockState(pos2, Blocks.LAVA.getDefaultState());
                                world.updateNeighbor(pos2, Blocks.LAVA, pos2);
                            }
                        }
                    }
                }
            }
        }
    }

}
