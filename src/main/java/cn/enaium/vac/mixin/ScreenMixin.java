package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.vac.client.event.RenderTooltipEvent;
import cn.enaium.vac.client.util.ChatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enaium
 */
@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Shadow
    public abstract List<Text> getTooltipFromItem(ItemStack stack);

    @Shadow
    public abstract void renderTooltip(MatrixStack matrices, List<Text> lines, int x, int y);

    @Inject(at = @At("HEAD"), method = "renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/item/ItemStack;II)V", cancellable = true)
    private void renderTooltip(MatrixStack matrices, ItemStack stack, int x, int y, CallbackInfo ci) {
        List<Text> tooltipFromItem = this.getTooltipFromItem(stack);
        CF4M.EVENT.post(new RenderTooltipEvent(matrices, stack, (ArrayList<Text>) tooltipFromItem, x, y));
        this.renderTooltip(matrices, tooltipFromItem, x, y);
        ci.cancel();
    }
}
