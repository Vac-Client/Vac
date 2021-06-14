package cn.enaium.vac.client.module.movement

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.MOVEMENT
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket

/**
 * @author Enaium
 */
@Module("NoFall", type = MOVEMENT)
class NoFall {
    @Event
    fun on(motioningEvent: MotioningEvent) {
        if (mc.player!!.fallDistance <= 2) return
        mc.player!!.networkHandler.sendPacket(PlayerMoveC2SPacket.OnGroundOnly(true))
    }
}