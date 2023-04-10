package net.drugunMC.aggregate;

import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;

public final class AggregateJsonCondProvider {

    public static final Identifier READCONFIG = new Identifier(AggregateMain.ModID + ":readconfig");

    public static ConditionJsonProvider readconfig(String key) {
        return AggregateJsonCondImpl.config(READCONFIG, key);
    }

    static {

        ResourceConditions.register(READCONFIG, AggregateJsonCondImpl::readConfig);

    }

    static void init() {
    }

    public AggregateJsonCondProvider() {
    }









}

