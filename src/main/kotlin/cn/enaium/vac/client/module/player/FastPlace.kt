package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.imc
import cn.enaium.vac.client.module.PLAYER

/**
 * @author Enaium
 */
@Module("FastPlace", type = PLAYER)
class FastPlace {
    @Event
    fun on(motioningEvent: MotioningEvent) {
        imc.instance.itemUseCooldown = 0
    }
}