package net.drugunMC.aggregate;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;


@Config(name = "aggregate-config", wrapperName = "AggregateConfig")
@Modmenu(modId = AggregateMain.ModID)
@Sync(Option.SyncMode.OVERRIDE_CLIENT)
public class AggregateConfigModel {


    public static boolean predicatePositive(int in) {
        return in >= 0;
    }



    @RestartRequired
    public boolean javelinRecipe = true;

    @RestartRequired
    public boolean animalBones = true;

    @RestartRequired
    public boolean safeItemsExplosion = true;

    @RestartRequired
    @PredicateConstraint("predicatePositive")
    public int suffocationTimer = 200;

    @RestartRequired
    public boolean arrowRecipe = true;

    @RestartRequired
    public boolean mudSlow = true;

    @RestartRequired
    public boolean sugarCane = true;

    @RestartRequired
    public boolean safeFire = true;

    @RestartRequired
    public boolean safeCreepers = true;

    @RestartRequired
    public boolean safeItemsFire = true;

    @RestartRequired
    public boolean frogMixin = true;

    @RestartRequired
    public boolean leatherRecipe = true;

    @RestartRequired
    public boolean improveItemMaterials = true;

    @RestartRequired
    public boolean improveArmourMaterials = true;

    @RestartRequired
    public boolean infiniteRepair = true;

    @RestartRequired
    public boolean armourWeight = true;

    @RestartRequired
    public boolean armourWeightLilyPad = true;

    @RestartRequired
    public boolean armourWeightMud = true;

    @RestartRequired
    public boolean armourWeightMoss = true;

    @RestartRequired
    public boolean armourWeightFallingBlocks = true;

    @RestartRequired
    @RangeConstraint(min = 0, max = 20)
    public int armourWeightHeavy = 7;

    @RestartRequired
    public boolean betterMovement = true;

    @RestartRequired
    public boolean waterFloating = true;

    @RestartRequired
    public boolean iceFallBreaking = true;

    @RestartRequired
    public boolean magmaFallBreaking = true;

    @RestartRequired
    public boolean iceWeightBreaking = true;

    @RestartRequired
    public boolean magmaWeightBreaking = true;

    @RestartRequired
    public boolean mudBonemeal = true;

    @RestartRequired
    public boolean climbingEnabled = true;

    @RestartRequired
    public boolean climbingLenient = true;

    @RestartRequired
    public boolean climbingWeightCheck = true;

    @RestartRequired
    @RangeConstraint(min = -90, max = 90)
    public float climbingAngle = -45f;

    @RestartRequired
    public boolean disableKnockback = true;

    @RestartRequired
    public boolean strongerTNT = true;

    @RestartRequired
    @RangeConstraint(min = 0.2, max = 3)
    public float basePlayerMovementMult = 1f;

    @RestartRequired
    @RangeConstraint(min = 0.1, max = 3)
    public float playerSprintSpeed = 0.3f;




}

