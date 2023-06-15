package net.drugunMC.aggregate.entity.projectile;

import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class JavelinStoneEntity extends PersistentProjectileEntity {
    private ItemStack javelinStack = new ItemStack(AggregateMain.JAVELIN_STONE);
    private boolean dealtDamage;

    public JavelinStoneEntity(EntityType<? extends JavelinStoneEntity> entityType, World world) {
        super(entityType, world);
    }

    public JavelinStoneEntity(World world, LivingEntity owner) {
        super(AggregateMain.JavelinStoneEntityType, owner, world);
        this.javelinStack.setCount(1);
    }

    public JavelinStoneEntity(World world, double x, double y, double z) {
        super(AggregateMain.JavelinStoneEntityType, x, y, z, world);
    }

    public JavelinStoneEntity(World world, LivingEntity owner, ItemStack stack) {
        super(AggregateMain.JavelinStoneEntityType, owner, world);
        this.javelinStack = stack.copy();
        javelinStack.setCount(1);
    }


    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        super.tick();

    }


    @Override
    protected ItemStack asItemStack() {
        return this.javelinStack.copy();
    }


    @Override
    @Nullable
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        if (this.dealtDamage) {
            return null;
        }
        return super.getEntityCollision(currentPosition, nextPosition);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity2 = this.getOwner();
        Entity entity = entityHitResult.getEntity();
        float dmg = 5.0f;
        DamageSource damageSource = this.getDamageSources().trident(this, (Entity)(entity2 == null ? this : entity2));
        this.dealtDamage = true;
        SoundEvent soundEvent = SoundEvents.ITEM_TRIDENT_HIT;
        if (entity.damage(damageSource, dmg)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity2 = (LivingEntity)entity;
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity)entity2, livingEntity2);
                }
                this.onHit(livingEntity2);
            }
        }
        this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));
        float g = 1.0f;
        this.playSound(soundEvent, g, 1.0f);
    }


    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("JavelinStone", NbtElement.COMPOUND_TYPE)) {
            this.javelinStack = ItemStack.fromNbt(nbt.getCompound("JavelinStone"));
        }
        this.dealtDamage = nbt.getBoolean("DealtDamage");

    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("JavelinStone", this.javelinStack.writeNbt(new NbtCompound()));
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    public void age() {
        if (this.pickupType != PersistentProjectileEntity.PickupPermission.ALLOWED) {
            super.age();
        }
    }

    @Override
    protected float getDragInWater() {
        return 0.80f;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }











}
