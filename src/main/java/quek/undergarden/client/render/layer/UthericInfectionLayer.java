package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGAttachments;

public class UthericInfectionLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/utheric_infection_overlay.png");

	public UthericInfectionLayer(RenderLayerParent<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
		double infectionLevel = entity.getData(UGAttachments.UTHERIC_INFECTION.get());
		if (infectionLevel > 0) {
			VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
			this.getParentModel().renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, FastColor.ARGB32.color((int) (infectionLevel * 2) + 128, 255, 255, 255));
		}
	}
}