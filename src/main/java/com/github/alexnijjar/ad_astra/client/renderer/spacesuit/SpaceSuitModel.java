package com.github.alexnijjar.ad_astra.client.renderer.spacesuit;

import com.github.alexnijjar.ad_astra.util.ModIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SpaceSuitModel extends AbstractSpaceSuitModel {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new ModIdentifier("space_suit_p1"), "main");

	public <T extends LivingEntity> SpaceSuitModel(ModelPart root, BipedEntityModel<T> contextModel, Identifier headTexture) {
		super(root, contextModel, headTexture);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new Dilation(0.5f)).uv(0, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, new Dilation(0.75f)),
				ModelTransform.of(0.0f, 0.0f, 0.0f, -0.0175f, 0.0873f, 0.0f));

		modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 32).cuboid(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, new Dilation(0.25f)).uv(28, 28).cuboid(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, new Dilation(0.25f)).uv(50, 29)
				.cuboid(-3.0f, 5.0f, -2.5f, 6.0f, 4.0f, 1.0f, new Dilation(0.25f)).uv(0, 55).cuboid(-2.5f, 1.0f, 2.55f, 5.0f, 8.0f, 1.0f, new Dilation(0.75f)), ModelTransform.pivot(0.0f, 0.0f, 0.0f));

		modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(20, 44).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(0.26f)), ModelTransform.pivot(-5.0f, 2.0f, 0.0f));

		modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 0).cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(0.26f)), ModelTransform.pivot(5.0f, 2.0f, 0.0f));

		modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(48, 44).cuboid(-2.0f, 6.0f, -2.0f, 4.0f, 6.0f, 4.0f, new Dilation(0.4f)).uv(48, 54).cuboid(-2.0f, 6.0f, -2.0f, 4.0f, 6.0f, 4.0f, new Dilation(0.27f)),
				ModelTransform.pivot(-2.0f, 12.0f, 0.0f));

		modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(48, 44).cuboid(-2.0f, 6.0f, -2.0f, 4.0f, 6.0f, 4.0f, new Dilation(0.4f)).uv(48, 54).cuboid(-2.0f, 6.0f, -2.0f, 4.0f, 6.0f, 4.0f, new Dilation(0.27f)),
				ModelTransform.pivot(2.0f, 12.0f, 0.0f));

		return TexturedModelData.of(modelData, 64, 64);
	}
}