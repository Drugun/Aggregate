package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(ItemStack.class)
public abstract class ItemRepairMixin {


	@ModifyVariable(method = "setRepairCost", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	public int injectedA(int repairCost){
		if( AggregateMain.CONFIG.infiniteRepair()){
			return 0;
		}
		return repairCost;
	}





}