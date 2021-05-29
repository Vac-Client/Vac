package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.vac.client.event.KeyboardEvent;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Enaium
 */
@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(at = @At("HEAD"), method = "onKey", cancellable = true)
    private void onKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        if (i == GLFW.GLFW_PRESS) {
            if (MinecraftClient.getInstance().currentScreen == null) {
                CF4M.MODULE.onKey(key);
            }

            KeyboardEvent keyboardEvent = new KeyboardEvent(key);
            CF4M.EVENT.post(keyboardEvent);
            if (keyboardEvent.getCancel()) {
                ci.cancel();
            }
        }
    }
}
