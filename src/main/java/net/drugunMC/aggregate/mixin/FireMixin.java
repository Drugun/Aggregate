package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(FireBlock.class)
public abstract class FireMixin extends AbstractFireBlock {

    public FireMixin(AbstractBlock.Settings settings) {
        super(settings, 1);
    }


    @Inject(method = "getBurnChance(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)I", at = @At("HEAD"), cancellable = true)
    private void injected1(WorldView world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if( AggregateMain.CONFIG.safeFire()){
            cir.setReturnValue(0);
            cir.cancel();
        }

    }

    @Inject(method = "trySpreadingFire(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/util/math/random/Random;I)V", at = @At("HEAD"), cancellable = true)
    private void injected3(World world, BlockPos pos, int spreadFactor, Random random, int currentAge, CallbackInfo ci) {
        if( AggregateMain.CONFIG.safeFire()){
            BlockState blockState = world.getBlockState(pos);
            Block block = blockState.getBlock();
            if (block instanceof TntBlock) {
                TntBlock.primeTnt(world, pos);
                world.removeBlock(pos, false);
            }
            ci.cancel();
        }


    }

    @Inject(method = "scheduledTick", at = @At("HEAD"))
    private void injected2(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if( AggregateMain.CONFIG.safeFire()){
            if(random.nextInt(50) == 1){
                world.removeBlock(pos, false);
            }
        }


    }

}
