package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;


@Mixin(ToolMaterials.class)
public abstract class ToolMaterialsMixin {





	@Inject(method = "<init>", at = @At("RETURN"))
	public void injectedA(String string, int i, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient, CallbackInfo ci){
		if( AggregateMain.CONFIG.improveItemMaterials() ){
			if( ((ToolMaterials)(Object)this).itemDurability == 59 ){    // wood
				((ToolMaterials)(Object)this).itemDurability = 200;
				((ToolMaterials)(Object)this).miningSpeed = 3.0F;
				((ToolMaterials)(Object)this).miningLevel = 1;
				((ToolMaterials)(Object)this).enchantability = 20;
				((ToolMaterials)(Object)this).attackDamage = 0.0F;
			}
			else if( ((ToolMaterials)(Object)this).itemDurability == 131 ){    // stone
				((ToolMaterials)(Object)this).itemDurability = 350;
				((ToolMaterials)(Object)this).miningSpeed = 4.5F;
				((ToolMaterials)(Object)this).miningLevel = 2;
				((ToolMaterials)(Object)this).enchantability = 12;
				((ToolMaterials)(Object)this).attackDamage = 1.0F;
			}
			else if( ((ToolMaterials)(Object)this).itemDurability == 250 ){    // iron
				((ToolMaterials)(Object)this).itemDurability = 500;
//			((ToolMaterials)(Object)this).miningSpeed = 6.0F;
				((ToolMaterials)(Object)this).miningLevel = 3;
//			((ToolMaterials)(Object)this).enchantability = 14;
//			((ToolMaterials)(Object)this).attackDamage = 2.0F;
			}
			else if( ((ToolMaterials)(Object)this).itemDurability == 32 ){    // gold
				((ToolMaterials)(Object)this).itemDurability = 200;
//			((ToolMaterials)(Object)this).miningSpeed = 12.0F;
				((ToolMaterials)(Object)this).miningLevel = 3;
//			((ToolMaterials)(Object)this).enchantability = 22;
				((ToolMaterials)(Object)this).attackDamage = 4.0F;
			}
		}




	}










}