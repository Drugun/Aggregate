package net.drugunMC.aggregate.mixin;


import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SandBlock.class)
public abstract class SandBlockMixin extends FallingBlock {



    public SandBlockMixin(Settings settings) {
        super(settings);
    }



    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity){
        if( entity instanceof PlayerEntity ){
            if( ((PlayerEntity)entity).getArmourWeight() >= AggregateMain.CONFIG.armourWeightHeavy() ){
                world.createAndScheduleBlockTick(pos, this, this.getFallDelay());
            }
        }
    }


}
