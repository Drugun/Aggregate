package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.entity.*;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntEntity.class)
public abstract class TNTMixin extends Entity {




    public TNTMixin(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);

    }



    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void injected1(CallbackInfo ci) {
        if( AggregateMain.CONFIG.strongerTNT() ){
            ((TntEntity)(Object)this).world.createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), 8.0f, Explosion.DestructionType.BREAK);
            ci.cancel();
        }
    }





}
