package net.drugunMC.aggregate.mixin;


import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CarpetBlock.class)
public abstract class MossCarpetMixin extends Block {


    @Shadow @Final protected static VoxelShape SHAPE;
    private static final VoxelShape EMPTY_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);



    public MossCarpetMixin(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if( world.getBlockState(pos).isOf(Blocks.MOSS_CARPET) && AggregateMain.CONFIG.armourWeightMoss() ){
            if(context instanceof EntityShapeContext){
                if( ((EntityShapeContext) context).getEntity() instanceof PlayerEntity){
                    if( ((PlayerEntity) ((EntityShapeContext) context).getEntity()).getArmourWeight() >= AggregateMain.CONFIG.armourWeightHeavy() ){
                        return EMPTY_SHAPE;
                    }
                }

            }
        }
        return SHAPE;
    }


}
