package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GloomperModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.GloomperEyesLayer;
import quek.undergarden.entity.animal.Gloomper;

public class GloomperRenderer extends MobRenderer<Gloomper, GloomperModel<Gloomper>> {

	public static final ResourceLocation GLOOMPER = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/gloomper.png");

	public GloomperRenderer(EntityRendererProvider.Context context) {
		super(context, new GloomperModel<>(context.bakeLayer(UGModelLayers.GLOOMPER)), 1.0F);
		this.addLayer(new GloomperEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Gloomper entity) {
		return GLOOMPER;
	}
}