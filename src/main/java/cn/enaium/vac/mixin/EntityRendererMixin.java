package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.vac.client.event.EntityRenderEvent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Enaium
 */
@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
    @Inject(at = @At("HEAD"), method = "render")
    private <T extends Entity> void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        CF4M.EVENT.post(new EntityRenderEvent(entity, yaw, tickDelta, matrices, vertexConsumers, light));
    }
}
