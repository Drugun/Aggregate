package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


@Mixin(CreeperEntity.class)
public abstract class CreeperMixin extends HostileEntity {

    public CreeperMixin(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }


    @ModifyArg(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"), index = 5)
    private Explosion.DestructionType injected(Explosion.DestructionType destructionType) {
        if( AggregateMain.CONFIG.safeCreepers()){
            return Explosion.DestructionType.NONE;
        }
        else {
            return destructionType;
        }
    }


}
