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

@Mixin(LilyPadBlock.class)
public class LilyPadMixin extends PlantBlock {


    @Shadow @Final protected static VoxelShape SHAPE;
    private static final VoxelShape EMPTY_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);



    public LilyPadMixin(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if( AggregateMain.CONFIG.armourWeight() ){
            if(context instanceof EntityShapeContext){
                if( ((EntityShapeContext) context).getEntity() instanceof PlayerEntity){
                    if( ((PlayerEntity) ((EntityShapeContext) context).getEntity()).getHeavy() ){
                        return EMPTY_SHAPE;
                    }
                }

            }
        }
        return SHAPE;
    }


}
