package cn.enaium.vac.mixin;

import cn.enaium.cf4m.CF4M;
import cn.enaium.vac.client.event.AttackBlockEvent;
import cn.enaium.vac.client.event.BlockBreakingProgressEvent;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Enaium
 */
@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Inject(at = @At(value = "HEAD"), method = "attackBlock", cancellable = true)
    private void attackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        AttackBlockEvent attackBlockEvent = new AttackBlockEvent(pos, direction);
        CF4M.EVENT.post(attackBlockEvent);
        if (attackBlockEvent.getCancel()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "updateBlockBreakingProgress", cancellable = true)
    private void updateBlockBreakingProgress(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        BlockBreakingProgressEvent blockBreakingProgressEvent = new BlockBreakingProgressEvent(pos, direction);
        CF4M.EVENT.post(blockBreakingProgressEvent);
        if (blockBreakingProgressEvent.getCancel()) {
            cir.setReturnValue(false);
        }
    }
}
