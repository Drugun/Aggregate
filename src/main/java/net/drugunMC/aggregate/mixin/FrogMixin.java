package net.drugunMC.aggregate.mixin;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FrogEntity.class)
public abstract class FrogMixin extends AnimalEntity {






    public FrogMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);

    }



    @Inject(method = "initialize", at = @At("TAIL"))
    private void injected(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        if( AggregateMain.CONFIG.frogMixin()){

            int i = world.getRandom().nextInt(3);
            if(i==0){
                ((FrogEntity)(Object)this).setVariant(FrogVariant.WARM);
            }
            else if(i == 1){
                ((FrogEntity)(Object)this).setVariant(FrogVariant.TEMPERATE);
            }
            else {
                ((FrogEntity)(Object)this).setVariant(FrogVariant.COLD);
            }


        }

    }






}
