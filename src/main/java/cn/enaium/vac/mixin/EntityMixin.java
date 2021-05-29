package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Enaium
 */
@Mixin(Entity.class)
public class EntityMixin {
    @Inject(at = @At("HEAD"), method = "isTouchingWater", cancellable = true)
    private void isTouchingWater(CallbackInfoReturnable<Boolean> cir) {
        if (CF4M.MODULE.getByName("Flight").getEnable()) {
            cir.setReturnValue(false);
        }
    }
}
