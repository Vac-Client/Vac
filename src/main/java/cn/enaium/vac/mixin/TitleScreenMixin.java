package cn.enaium.vac.mixin;

import cn.enaium.vac.client.screen.AltScreen;
import cn.enaium.vac.client.screen.CreditScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Enaium
 */
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "init()V")
    private void init(CallbackInfo callbackInfo) {
        addButton(new ButtonWidget(5, 5, 20, 20, new LiteralText("Alt"), button -> MinecraftClient.getInstance().openScreen(new AltScreen())));
        addButton(new ButtonWidget(35, 5, 40, 20, new LiteralText("Credit"), button -> {
            CreditScreen.INSTANCE.setCredit(true);
            MinecraftClient.getInstance().openScreen(new CreditsScreen(false, () -> {
            }));
        }));
    }
}
