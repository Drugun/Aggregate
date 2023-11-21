package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ItemEntity.class)
public abstract class FireproofItemsMixin extends Entity {

	public FireproofItemsMixin(EntityType<? extends ItemEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
	public void injectedA(CallbackInfoReturnable<Boolean> cir){
		if( AggregateMain.CONFIG.safeItemsFire()){
			cir.setReturnValue(true);
			cir.cancel();
		}


	}

	@Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
	private void injectedB(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if( AggregateMain.CONFIG.safeItemsExplosion()){
			if(source.isExplosive()){
				cir.setReturnValue(false);
				cir.cancel();
			}
		}

	}








}