package cn.enaium.vac.client.module.movement

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.MOVEMENT
import cn.enaium.vac.client.setting.DoubleSetting
import cn.enaium.vac.client.setting.FloatSetting
import org.lwjgl.glfw.GLFW


/**
 * @author Enaium
 */
@Module("Flight", key = GLFW.GLFW_KEY_G, type = MOVEMENT)
class Flight {

    @Setting("speed")
    val speed = DoubleSetting(1.0, 0.05, 5.0)

    @Event
    fun onMotion(motioningEvent: MotioningEvent) {
        mc.player!!.abilities.flying = false
        mc.player!!.flyingSpeed = speed.current.toFloat()

        mc.player!!.setVelocity(0.0, 0.0, 0.0)

        if (mc.options.keyJump.isPressed)
            mc.player!!.velocity = mc.player!!.velocity.add(0.0, speed.current, 0.0)

        if (mc.options.keySneak.isPressed)
            mc.player!!.velocity = mc.player!!.velocity.subtract(0.0, speed.current, 0.0)
    }
}