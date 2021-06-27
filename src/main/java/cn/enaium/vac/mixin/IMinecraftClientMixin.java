package cn.enaium.vac.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.util.Session;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Enaium
 */
@Mixin(MinecraftClient.class)
public interface IMinecraftClientMixin {
    @Accessor
    int getItemUseCooldown();

    @Accessor
    void setItemUseCooldown(int itemUseCooldown);
}
