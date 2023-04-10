package net.drugunMC.aggregate.mixin;


import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IceBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin extends TransparentBlock {

    public IceBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance){
        if( !world.isClient && (AggregateMain.CONFIG.armourWeight() || AggregateMain.CONFIG.iceBreaking()) ){
            if( entity instanceof PlayerEntity){
                if( (AggregateMain.CONFIG.armourWeight() && ((PlayerEntity)entity).getHeavy()) || (AggregateMain.CONFIG.iceBreaking() && fallDistance > 2) ){
                    ((PlayerEntity) entity).setJumpPreventionTicks(20);

                    world.breakBlock(pos, false);
                    world.setBlockState(pos, Blocks.WATER.getDefaultState());
                    world.updateNeighbor(pos, Blocks.WATER, pos);
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
                            if(world.getBlockState(pos2.south()).isOf(Blocks.ICE)){
                                world.breakBlock(pos2.south(), false);
                                world.setBlockState(pos2.south(), Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos2.south(), Blocks.WATER, pos2.south());
                            }
                            if(world.getBlockState(pos.south()).isOf(Blocks.ICE)){
                                world.breakBlock(pos.south(), false);
                                world.setBlockState(pos.south(), Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos.south(), Blocks.WATER, pos.south());
                            }
                        }
                        else{
                            if(world.getBlockState(pos2.north()).isOf(Blocks.ICE)){
                                world.breakBlock(pos2.north(), false);
                                world.setBlockState(pos2.north(), Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos2.north(), Blocks.WATER, pos2.north());
                            }
                            if(world.getBlockState(pos.north()).isOf(Blocks.ICE)){
                                world.breakBlock(pos.north(), false);
                                world.setBlockState(pos.north(), Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos.north(), Blocks.WATER, pos.north());
                            }
                        }
                        if(world.getBlockState(pos2).isOf(Blocks.ICE)){
                            world.breakBlock(pos2, false);
                            world.setBlockState(pos2, Blocks.WATER.getDefaultState());
                            world.updateNeighbor(pos2, Blocks.WATER, pos2);
                        }
                    }
                    else{
                        pos2 = pos.east();
                        if(z > 0.5f){
                            if(world.getBlockState(pos2.south()).isOf(Blocks.ICE)){
                                world.breakBlock(pos2.south(), false);
                                world.setBlockState(pos2.south(), Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos2.south(), Blocks.WATER, pos2.south());
                            }
                            if(world.getBlockState(pos.south()).isOf(Blocks.ICE)){
                                world.breakBlock(pos.south(), false);
                                world.setBlockState(pos.south(), Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos.south(), Blocks.WATER, pos.south());
                            }
                        }
                        else{
                            if(world.getBlockState(pos2.north()).isOf(Blocks.ICE)){
                                world.breakBlock(pos2.north(), false);
                                world.setBlockState(pos2.north(), Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos2.north(), Blocks.WATER, pos2.north());
                            }
                            if(world.getBlockState(pos.north()).isOf(Blocks.ICE)){
                                world.breakBlock(pos.north(), false);
                                world.setBlockState(pos.north(), Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos.north(), Blocks.WATER, pos.north());
                            }
                        }
                        if(world.getBlockState(pos2).isOf(Blocks.ICE)){
                            world.breakBlock(pos2, false);
                            world.setBlockState(pos2, Blocks.WATER.getDefaultState());
                            world.updateNeighbor(pos2, Blocks.WATER, pos2);
                        }
                    }
                }
            }
        }
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity){
        if( !world.isClient && AggregateMain.CONFIG.armourWeight() ){
            if( entity instanceof PlayerEntity){
                if( ((PlayerEntity)entity).getHeavy() ){
                    if(world.random.nextInt(50) == 0){
                        ((PlayerEntity) entity).setJumpPreventionTicks(20);

                        world.breakBlock(pos, false);
                        world.setBlockState(pos, Blocks.WATER.getDefaultState());
                        world.updateNeighbor(pos, Blocks.WATER, pos);
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
                                if(world.getBlockState(pos2.south()).isOf(Blocks.ICE)){
                                    world.breakBlock(pos2.south(), false);
                                    world.setBlockState(pos2.south(), Blocks.WATER.getDefaultState());
                                    world.updateNeighbor(pos2.south(), Blocks.WATER, pos2.south());
                                }
                                if(world.getBlockState(pos.south()).isOf(Blocks.ICE)){
                                    world.breakBlock(pos.south(), false);
                                    world.setBlockState(pos.south(), Blocks.WATER.getDefaultState());
                                    world.updateNeighbor(pos.south(), Blocks.WATER, pos.south());
                                }
                            }
                            else{
                                if(world.getBlockState(pos2.north()).isOf(Blocks.ICE)){
                                    world.breakBlock(pos2.north(), false);
                                    world.setBlockState(pos2.north(), Blocks.WATER.getDefaultState());
                                    world.updateNeighbor(pos2.north(), Blocks.WATER, pos2.north());
                                }
                                if(world.getBlockState(pos.north()).isOf(Blocks.ICE)){
                                    world.breakBlock(pos.north(), false);
                                    world.setBlockState(pos.north(), Blocks.WATER.getDefaultState());
                                    world.updateNeighbor(pos.north(), Blocks.WATER, pos.north());
                                }
                            }
                            if(world.getBlockState(pos2).isOf(Blocks.ICE)){
                                world.breakBlock(pos2, false);
                                world.setBlockState(pos2, Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos2, Blocks.WATER, pos2);
                            }
                        }
                        else{
                            pos2 = pos.east();
                            if(z > 0.5f){
                                if(world.getBlockState(pos2.south()).isOf(Blocks.ICE)){
                                    world.breakBlock(pos2.south(), false);
                                    world.setBlockState(pos2.south(), Blocks.WATER.getDefaultState());
                                    world.updateNeighbor(pos2.south(), Blocks.WATER, pos2.south());
                                }
                                if(world.getBlockState(pos.south()).isOf(Blocks.ICE)){
                                    world.breakBlock(pos.south(), false);
                                    world.setBlockState(pos.south(), Blocks.WATER.getDefaultState());
                                    world.updateNeighbor(pos.south(), Blocks.WATER, pos.south());
                                }
                            }
                            else{
                                if(world.getBlockState(pos2.north()).isOf(Blocks.ICE)){
                                    world.breakBlock(pos2.north(), false);
                                    world.setBlockState(pos2.north(), Blocks.WATER.getDefaultState());
                                    world.updateNeighbor(pos2.north(), Blocks.WATER, pos2.north());
                                }
                                if(world.getBlockState(pos.north()).isOf(Blocks.ICE)){
                                    world.breakBlock(pos.north(), false);
                                    world.setBlockState(pos.north(), Blocks.WATER.getDefaultState());
                                    world.updateNeighbor(pos.north(), Blocks.WATER, pos.north());
                                }
                            }
                            if(world.getBlockState(pos2).isOf(Blocks.ICE)){
                                world.breakBlock(pos2, false);
                                world.setBlockState(pos2, Blocks.WATER.getDefaultState());
                                world.updateNeighbor(pos2, Blocks.WATER, pos2);
                            }
                        }
                    }
                }
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

}
