package cn.enaium.vac.client.module.movement

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Disable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.MOVEMENT

/**
 * @author Enaium
 */
@Module("AutoWalk", type = MOVEMENT)
class AutoWalk {
    @Event
    fun onMotion(motioningEvent: MotioningEvent) {
        mc.options.keyForward.isPressed = true
    }

    @Disable
    fun onDisable() {
        mc.options.keyForward.isPressed = false
    }

}