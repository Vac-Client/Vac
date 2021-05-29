package cn.enaium.vac.client.module.combat

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.COMBAT
import cn.enaium.vac.client.module.Mod
import cn.enaium.vac.client.setting.ModeSetting
import net.minecraft.entity.LivingEntity
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.hit.HitResult

/**
 * @author Enaium
 */
@Module("Critical", type = COMBAT)
class Critical {

    @Setting("Mode")
    private val mode = ModeSetting("Packet", arrayListOf("Packet", "LowJump", "Jump"))

    @Event
    fun onMotion(motioningEvent: MotioningEvent) {
        CF4M.MODULE.getByInstance(this).getExtend<Mod>().tag = mode.current
        if (mc.crosshairTarget == null || mc.crosshairTarget!!.type != HitResult.Type.ENTITY || (mc.crosshairTarget as EntityHitResult).entity !is LivingEntity)
            return

        if (!mc.player!!.isOnGround)
            return

        if (mc.player!!.isTouchingWater || mc.player!!.isInLava)
            return

        when (mode.current) {
            "Packet" -> {
                val posX = mc.player!!.x
                val posY = mc.player!!.y
                val posZ = mc.player!!.z

                sendPos(posX, posY + 0.0625, posZ, true)
                sendPos(posX, posY, posZ, false)
                sendPos(posX, posY + 1.1E-5, posZ, false)
                sendPos(posX, posY, posZ, false)
            }
            "LowJump" -> {
                mc.player!!.addVelocity(0.0, 0.1, 0.0)
                mc.player!!.fallDistance = 0.1F
                mc.player!!.isOnGround = false
            }
            "Jump" -> {
                mc.player!!.jump()
            }
        }
    }

    private fun sendPos(x: Double, y: Double, z: Double, onGround: Boolean) {
        mc.player!!.networkHandler.sendPacket(
            PlayerMoveC2SPacket.PositionOnly(x, y, z, onGround)
        )
    }
}