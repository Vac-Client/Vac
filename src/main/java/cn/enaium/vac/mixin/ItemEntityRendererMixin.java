package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.vac.client.event.RenderItemEntityEvent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Enaium
 */
@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {
    @Inject(at = @At(value = "HEAD"), method = "render", cancellable = true)
    private void render(ItemEntity itemEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo callbackInfo) {
        RenderItemEntityEvent event = new RenderItemEntityEvent(itemEntity, f, g, matrixStack, vertexConsumerProvider, i);
        CF4M.EVENT.post(event);
        if (event.getCancel()) {
            callbackInfo.cancel();
        }
    }
}
