package cn.enaium.vac.mixin;

import cn.enaium.vac.client.screen.CreditScreen;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Enaium
 */
@Mixin(CreditsScreen.class)
class CreditsScreenMixin {
    @Shadow
    private List<OrderedText> credits;

    @Shadow
    private IntSet centeredLines;

    @Inject(at = @At("HEAD"), method = "init", cancellable = true)
    private void init(CallbackInfo ci) {

        if (!CreditScreen.INSTANCE.getCredit())
            return;

        CreditScreen.INSTANCE.setCredit(false);

        if (this.credits == null) {
            this.credits = Lists.newArrayList();
            this.centeredLines = new IntOpenHashSet();
            CreditScreen.INSTANCE.init((ArrayList<OrderedText>) credits, centeredLines);
            ci.cancel();
        }
    }
}
