package cn.enaium.vac.mixin;

import net.minecraft.world.World;
import net.minecraft.world.chunk.BlockEntityTickInvoker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * @author Enaium
 */
@Mixin(World.class)
public interface IWorldMixin {
    @Accessor
    List<BlockEntityTickInvoker> getBlockEntityTickers();
}
