package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SploogieModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.entity.monster.cavern.Sploogie;

public class SploogieRenderer extends MobRenderer<Sploogie, SploogieModel<Sploogie>> {

	private static final ResourceLocation SPLOOGIE = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/sploogie.png");

	public SploogieRenderer(EntityRendererProvider.Context context) {
		super(context, new SploogieModel<>(context.bakeLayer(UGModelLayers.SPLOOGIE)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(Sploogie entity) {
		return SPLOOGIE;
	}
}
