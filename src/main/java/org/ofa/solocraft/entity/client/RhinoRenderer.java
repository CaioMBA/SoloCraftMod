package org.ofa.solocraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.entity.custom.RhinoEntity;

public class RhinoRenderer extends MobRenderer<RhinoEntity, RhinoModel<RhinoEntity>> {
    public RhinoRenderer(EntityRendererProvider.Context pContext) {
        super(
                pContext,
                new RhinoModel<>(pContext.bakeLayer(ModModelLayers.RHINO_LAYER)),
                2.0F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull RhinoEntity rhinoEntity) {
        return new ResourceLocation(SolocraftMod.MOD_ID, "textures/entity/rhino.png");
    }

    @Override
    public void render(RhinoEntity pEntity,
                       float pEntityYaw,
                       float pPartialTicks,
                       @NotNull PoseStack pMatrixStack,
                       @NotNull MultiBufferSource pBuffer,
                       int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
