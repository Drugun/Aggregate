package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(LivingEntity.class)
public abstract class SuffocationMixin extends Entity {




    private int suffocationTimer = 1; // default 200 ticks = 10 seconds




    public SuffocationMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }



    @Redirect(method = "baseTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInsideWall()Z"))
    private boolean injected(LivingEntity instance) {
        if(instance.isInsideWall()){
            if(suffocationTimer > 0){
                suffocationTimer--;
                return false;
            }
            else{
                return true;
            }
        }
        else{
            suffocationTimer = AggregateMain.CONFIG.suffocationTimer();
            return false;
        }

    }


}