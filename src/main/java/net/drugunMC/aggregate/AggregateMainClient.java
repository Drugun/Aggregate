package net.drugunMC.aggregate;

import net.drugunMC.aggregate.client.render.entity.model.JavelinStoneEntityModel;
import net.drugunMC.aggregate.misc.JavelinStoneRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class AggregateMainClient implements ClientModInitializer {


    public static final String ModID = "aggregate";





    public static final EntityModelLayer JAVELIN_STONE_LAYER = new EntityModelLayer(new Identifier(ModID, "javelin_stone"), "javelin_stone");




    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {



        EntityModelLayerRegistry.registerModelLayer(JAVELIN_STONE_LAYER, JavelinStoneEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(AggregateMain.JavelinStoneEntityType, JavelinStoneRenderer::new);



    }


}
