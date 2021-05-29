package cn.enaium.vac.mixin;

import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Enaium
 */
@Mixin(WorldRenderer.class)
public interface IWorldRendererMixin {
    @Accessor
    BufferBuilderStorage getBufferBuilders();
}
