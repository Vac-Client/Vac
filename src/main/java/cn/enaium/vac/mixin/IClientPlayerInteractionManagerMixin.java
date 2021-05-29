package cn.enaium.vac.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * @author Enaium
 */
@Mixin(ClientPlayerInteractionManager.class)
public interface IClientPlayerInteractionManagerMixin {
    @Accessor("currentBreakingProgress")
    float getCurrentBreakingProgress();

    @Accessor("currentBreakingProgress")
    void setCurrentBreakingProgress(float currentBreakingProgress);

    @Accessor("blockBreakingCooldown")
    int getBlockBreakingCooldown();

    @Accessor("blockBreakingCooldown")
    void setBlockBreakingCooldown(int blockBreakingCooldown);

    @Invoker
    void invokeSendPlayerAction(PlayerActionC2SPacket.Action action, BlockPos pos, Direction direction);
}
