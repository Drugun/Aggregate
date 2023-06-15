package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;
import java.util.function.Supplier;


@Mixin(ArmorMaterials.class)
public abstract class ArmorMaterialsMixin {





	@Inject(method = "<init>", at = @At("RETURN"))
	public void injectedA(String string, int i, String name, int durabilityMultiplier, EnumMap protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier repairIngredientSupplier, CallbackInfo ci){
		if( AggregateMain.CONFIG.improveArmourMaterials() ){
			if( ((ArmorMaterials)(Object)this).durabilityMultiplier == 5 ){    // leather
				((ArmorMaterials)(Object)this).durabilityMultiplier = 12;
			}
			else if( ((ArmorMaterials)(Object)this).durabilityMultiplier == 15 ){    // iron, chain
				((ArmorMaterials)(Object)this).durabilityMultiplier = 20;
			}
			else if( ((ArmorMaterials)(Object)this).durabilityMultiplier == 7 ){    // gold
				((ArmorMaterials)(Object)this).durabilityMultiplier = 14;
			}
		}




	}










}