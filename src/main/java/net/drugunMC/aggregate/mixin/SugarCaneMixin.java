package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(SugarCaneBlock.class)
public abstract class SugarCaneMixin extends Block {






	public SugarCaneMixin(Settings settings) {
		super(settings);
	}



	@Inject(method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
	private void injected(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		if( AggregateMain.CONFIG.sugarCane()){
			if (world.getBlockState(pos.down()).isOf(Blocks.MUD) || world.getBlockState(pos.down()).isOf(Blocks.MUDDY_MANGROVE_ROOTS)) {
				cir.setReturnValue(true);
				cir.cancel();
			}
		}

	}






}