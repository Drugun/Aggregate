package net.drugunMC.aggregate.misc;

import net.drugunMC.aggregate.AggregateMain;
import net.drugunMC.aggregate.AggregateMainClient;
import net.drugunMC.aggregate.client.render.entity.model.JavelinStoneEntityModel;
import net.drugunMC.aggregate.entity.projectile.JavelinStoneEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

@Environment(value=EnvType.CLIENT)
public class JavelinStoneRenderer
        extends EntityRenderer<JavelinStoneEntity> {
    public static final Identifier TEXTURE = new Identifier(AggregateMain.ModID, "textures/entity/javelin_stone.png");
    private final JavelinStoneEntityModel model;

    public JavelinStoneRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new JavelinStoneEntityModel(context.getPart(AggregateMainClient.JAVELIN_STONE_LAYER));
    }

    @Override
    public void render(JavelinStoneEntity javelinEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, javelinEntity.prevYaw, javelinEntity.getYaw()) - 90.0f));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, javelinEntity.prevPitch, javelinEntity.getPitch()) + 90.0f));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(javelinEntity)), false, false);
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
        super.render(javelinEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(JavelinStoneEntity javelinStoneEntity) {
        return TEXTURE;
    }
}

