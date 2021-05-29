package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.BlockBreakingProgressEvent
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.imc
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.mixin.IClientPlayerInteractionManagerMixin
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

/**
 * @author Enaium
 */
@Module("FastBreak", type = PLAYER)
class FastBreak {
    @Event
    fun onUpdate(motioningEvent: MotioningEvent) {
        imc.interactionManager.blockBreakingCooldown = 0
    }

    @Event
    fun onBlockBreakingProgress(breakingProgressEvent: BlockBreakingProgressEvent) {
        if (imc.interactionManager.currentBreakingProgress >= 1)
            return
        val action: PlayerActionC2SPacket.Action = PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK
        val blockPos: BlockPos = breakingProgressEvent.pos
        val direction: Direction = breakingProgressEvent.direction
        imc.interactionManager.invokeSendPlayerAction(
            action,
            blockPos,
            direction
        )
    }
}