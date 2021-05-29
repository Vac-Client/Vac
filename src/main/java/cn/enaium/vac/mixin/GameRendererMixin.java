package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.vac.client.event.Render3DEvent;
import cn.enaium.vac.client.util.Render3DUtil;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
class GameRendererMixin {
    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", ordinal = 0), method = "renderWorld")
    private void renderWorld(float tickDelta, long limitTime, MatrixStack matrixStack, CallbackInfo ci) {
        Render3DUtil.INSTANCE.settings();
        CF4M.EVENT.post(new Render3DEvent(tickDelta, limitTime, matrixStack));
        Render3DUtil.INSTANCE.resets();
    }
}