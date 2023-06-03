package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.drugunMC.aggregate.JumpCooldownTimerInterface;
import net.drugunMC.aggregate.PlayerWeightInterface;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerWeightInterface, JumpCooldownTimerInterface {

    public int armourWeight = 0;

    public boolean hasJumpedFlag = false;

    public int jumpPreventionTicks = 0;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }



    @Inject(method = "tick", at = @At("HEAD"))
    private void injected1(CallbackInfo ci) {
        if(jumpPreventionTicks > 0){
            jumpPreventionTicks--;
        }
        else if( AggregateMain.CONFIG.betterMovement() ){
            if( ((PlayerEntity)(Object)this).isOnGround() && hasJumpedFlag){
                hasJumpedFlag = false;
                jumpPreventionTicks = 2;
            }
        }
        if( ((PlayerEntity)(Object)this).age % 100 == 0){
            if( AggregateMain.CONFIG.armourWeight() ){
                int arm = 0;
                Item armBody = ((PlayerEntity)(Object)this).getEquippedStack(EquipmentSlot.CHEST).getItem();
                Item armLegs = ((PlayerEntity)(Object)this).getEquippedStack(EquipmentSlot.LEGS).getItem();
                Item armHead = ((PlayerEntity)(Object)this).getEquippedStack(EquipmentSlot.HEAD).getItem();
                Item armFeet = ((PlayerEntity)(Object)this).getEquippedStack(EquipmentSlot.FEET).getItem();
                if( armBody != Items.AIR && armBody != Items.LEATHER_CHESTPLATE){
                    arm += 4;
                }
                if(armLegs != Items.AIR && armLegs != Items.LEATHER_LEGGINGS){
                    arm += 4;
                }
                if(armHead != Items.AIR && armHead != Items.LEATHER_HELMET && armHead != Items.TURTLE_HELMET){
                    arm += 2;
                }
                if(armFeet != Items.AIR && armFeet != Items.LEATHER_BOOTS){
                    arm += 2;
                }


                armourWeight = arm;

            }



        }


    }

    @Override
    public int getArmourWeight() {

            return armourWeight;

    }

    @Override
    public void setArmourWeight(int in) {

        armourWeight = in;

    }


    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void injected2(CallbackInfo ci){
        if(jumpPreventionTicks > 0){
            ci.cancel();
        }
        hasJumpedFlag = true;

    }

    @Override
    public int getJumpPreventionTicks() {
        return jumpPreventionTicks;
    }

    @Override
    public void setJumpPreventionTicks(int in) {
        jumpPreventionTicks = in;
    }

}
