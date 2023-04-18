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
    public boolean betterMovement = true;

    @RestartRequired
    public boolean waterFloating = true;

    @RestartRequired
    public boolean iceBreaking = true;

    @RestartRequired
    public boolean magmaBreaking = true;

    @RestartRequired
    public boolean mudBonemeal = true;

    @RestartRequired
    public boolean climbingEnabled = true;

    @RestartRequired
    @RangeConstraint(min = -90, max = 90)
    public float climbingAngle = -45f;

    @RestartRequired
    public boolean disableKnockback = true;








}

