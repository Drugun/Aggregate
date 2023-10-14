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
    public boolean javelinRecipe = false;

    @RestartRequired
    public boolean animalBones = false;

    @RestartRequired
    public boolean safeItemsExplosion = false;

    @RestartRequired
    @PredicateConstraint("predicatePositive")
    public int suffocationTimer = 5;

    @RestartRequired
    public boolean arrowRecipe = false;

    @RestartRequired
    public boolean mudSlow = false;

    @RestartRequired
    public boolean sugarCane = false;

    @RestartRequired
    public boolean safeFire = false;

    @RestartRequired
    public boolean safeCreepers = false;

    @RestartRequired
    public boolean safeItemsFire = false;

    @RestartRequired
    public boolean frogMixin = false;

    @RestartRequired
    public boolean leatherRecipe = false;

    @RestartRequired
    public boolean improveItemMaterials = false;

    @RestartRequired
    public boolean improveArmourMaterials = false;

    @RestartRequired
    public boolean infiniteRepair = false;

    @RestartRequired
    public boolean armourWeight = false;

    @RestartRequired
    public boolean armourWeightLilyPad = false;

    @RestartRequired
    public boolean armourWeightMud = false;

    @RestartRequired
    public boolean armourWeightMoss = false;

    @RestartRequired
    public boolean armourWeightFallingBlocks = false;

    @RestartRequired
    @RangeConstraint(min = 0, max = 20)
    public int armourWeightHeavy = 7;

    @RestartRequired
    public boolean betterMovement = false;

    @RestartRequired
    public boolean waterFloating = false;

    @RestartRequired
    public boolean iceFallBreaking = false;

    @RestartRequired
    public boolean magmaFallBreaking = false;

    @RestartRequired
    public boolean iceWeightBreaking = false;

    @RestartRequired
    public boolean magmaWeightBreaking = false;

    @RestartRequired
    public boolean mudBonemeal = false;

    @RestartRequired
    public boolean climbingEnabled = false;

    @RestartRequired
    public boolean climbingLenient = false;

    @RestartRequired
    public boolean climbingWeightCheck = false;

    @RestartRequired
    @RangeConstraint(min = -90, max = 90)
    public float climbingAngle = -45f;

    @RestartRequired
    public boolean disableKnockback = false;

    @RestartRequired
    public boolean strongerTNT = false;

    @RestartRequired
    @RangeConstraint(min = 0.2, max = 3)
    public float basePlayerMovementMult = 1f;

    @RestartRequired
    @RangeConstraint(min = 0.1, max = 3)
    public float playerSprintSpeed = 0.3f;

    @RestartRequired
    public boolean disallowReplaceWaterSource = false;

    @RestartRequired
    public boolean disallowReplaceWaterFlowing = false;

    @RestartRequired
    public boolean disallowReplaceLavaSource = false;

    @RestartRequired
    public boolean disallowReplaceLavaFlowing = false;

    @RestartRequired
    public boolean bucketExploitFix = false;

    @RestartRequired
    public boolean disallowReplaceFluidNonPlayer = false;


}

