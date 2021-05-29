package cn.enaium.vac.client.module.movement

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.MOVEMENT
import net.minecraft.client.network.ClientPlayerEntity
import org.lwjgl.glfw.GLFW


/**
 * @author Enaium
 */
@Module("AutoSprint", key = GLFW.GLFW_KEY_V, type = MOVEMENT)
class AutoSprint {
    @Event
    fun update(motioningEvent: MotioningEvent) {

        if (mc.player!!.horizontalCollision || mc.player!!.isSneaking) return

        if (mc.player!!.isWet || mc.player!!.isSubmergedInWater) return

        if (mc.player!!.forwardSpeed > 0) mc.player!!.isSprinting = true
    }
}