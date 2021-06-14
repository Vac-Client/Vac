package cn.enaium.vac.mixin;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Enaium
 */
@Mixin(KeyBinding.class)
public interface IKeyBindingMixin {
    @Accessor
    InputUtil.Key getBoundKey();
}
