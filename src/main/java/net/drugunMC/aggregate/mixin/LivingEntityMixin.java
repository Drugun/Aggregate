package net.drugunMC.aggregate.mixin;


import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {




    @Redirect(method = "jump", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isSprinting()Z"))
    private boolean injected1(LivingEntity instance) {
        if( AggregateMain.CONFIG.betterMovement() ){
            if( ((LivingEntity)(Object)this).getType() == EntityType.PLAYER ){
                return false;

            }

        }

        return ((LivingEntity)(Object)this).isSprinting();


    }


    @Inject(method = "hasNoDrag", at = @At("HEAD"), cancellable = true)
    private void injected2(CallbackInfoReturnable<Boolean> cir){
        if( AggregateMain.CONFIG.betterMovement() ){
            if( ((LivingEntity)(Object)this).getType() == EntityType.PLAYER  && ! ((LivingEntity)(Object)this).isOnGround() && ! ((PlayerEntity)(Object)this).getAbilities().flying && ! ((PlayerEntity)(Object)this).isClimbing() ){
                cir.setReturnValue(true);
                cir.cancel();

            }

        }

    }


    @Inject(method = "getMovementSpeed(F)F", at = @At("HEAD"), cancellable = true)
    private void injected3(float slipperiness, CallbackInfoReturnable<Float> cir){
        if( AggregateMain.CONFIG.betterMovement() && ((LivingEntity)(Object)this).getType() == EntityType.PLAYER ){
            if( !((LivingEntity)(Object)this).isOnGround() && ! ((PlayerEntity)(Object)this).getAbilities().flying && ! ((PlayerEntity)(Object)this).isClimbing() ){
                cir.setReturnValue(0.002f);
                cir.cancel();
            }

        }
    }


    @ModifyVariable(method = "applyFluidMovingSpeed", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private double injected4(double in) {
        if( AggregateMain.CONFIG.waterFloating() && ((LivingEntity)(Object)this).getType() == EntityType.PLAYER ){
            return 0.00f;
        }
        else {
            return in;
        }
    }


    @ModifyConstant(method = "applyFluidMovingSpeed", constant = @Constant(doubleValue = 0.003))
    private double injected5(double value) {
        if( AggregateMain.CONFIG.waterFloating() && ((LivingEntity)(Object)this).getType() == EntityType.PLAYER ){
            return 0.00f;
        }
        else{
            return -0.003f;
        }


    }


    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void injected6(CallbackInfoReturnable<Boolean> cir){
        if( ((LivingEntity)(Object)this).getType() == EntityType.PLAYER ){
            if( AggregateMain.CONFIG.mudSlow() ){
                if( ((LivingEntity)(Object)this).getBlockStateAtPos().isOf(Blocks.MUD) && (((LivingEntity)(Object)this).horizontalCollision || ((LivingEntity)(Object)this).world.getBlockState(((LivingEntity)(Object)this).getBlockPos().up()).isIn(BlockTags.CLIMBABLE) ) ){
                    cir.setReturnValue(true);
                    cir.cancel();
                }

            }
            if( AggregateMain.CONFIG.climbing() ){
                if( ((PlayerEntity)(Object)this).horizontalCollision ){
                    BlockPos pos = ((LivingEntity)(Object)this).getBlockPos();
                    if( isBlockClimbable(pos.north()) || isBlockClimbable(pos.north().up()) ){
                        cir.setReturnValue(true);
                        cir.cancel();
                    }
                    else if( isBlockClimbable(pos.south()) || isBlockClimbable(pos.south().up()) ){
                        cir.setReturnValue(true);
                        cir.cancel();
                    }
                    else if( isBlockClimbable(pos.east()) || isBlockClimbable(pos.east().up()) ){
                        cir.setReturnValue(true);
                        cir.cancel();
                    }
                    else if( isBlockClimbable(pos.west()) || isBlockClimbable(pos.west().up()) ){
                        cir.setReturnValue(true);
                        cir.cancel();
                    }
                }
            }
        }

    }

    public boolean isBlockClimbable(BlockPos pos){
        if( ((LivingEntity)(Object)this).world.getBlockState(pos).getCollisionShape(((LivingEntity)(Object)this).world, pos) != VoxelShapes.empty() ){
            if( ((LivingEntity)(Object)this).world.getBlockState(pos.up()).getCollisionShape(((LivingEntity)(Object)this).world, pos.up()) == VoxelShapes.empty() ){
                return true;
            }
        }
        return false;
    }





}
