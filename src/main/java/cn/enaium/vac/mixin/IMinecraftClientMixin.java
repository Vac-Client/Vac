package cn.enaium.vac.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.util.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Enaium
 */
@Mixin(MinecraftClient.class)
public interface IMinecraftClientMixin {
    @Accessor
    Session getSession();

    @Accessor
    void setSession(Session session);

    @Accessor
    int getItemUseCooldown();

    @Accessor
    void setItemUseCooldown(int itemUseCooldown);
}
