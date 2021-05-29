package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.cf4m.provider.ModuleProvider;
import cn.enaium.vac.client.setting.StringSetting;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Enaium
 */
@Mixin(SignEditScreen.class)
public abstract class SignEditScreenMixin {

    @Mutable
    @Shadow
    @Final
    private String[] text;

    @Shadow
    protected abstract void finishEditing();

    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo ci) {
        ModuleProvider autoSign = CF4M.MODULE.getByName("AutoSign");
        if (autoSign.getEnable()) {
            String[] texts = autoSign.getSetting().getByName("text").<StringSetting>getSetting().getCurrent().split("/n");
            List<String> s = new ArrayList<>();
            if (texts.length < 4) {
                Collections.addAll(s, texts);
                for (int i = 0; i < 4 - texts.length; i++) {
                    s.add("");
                }
            }
            text = s.toArray(new String[0]);
            System.out.println(Arrays.toString(text));
            finishEditing();
        }
    }
}
