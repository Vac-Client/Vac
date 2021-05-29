package cn.enaium.vac.client.module.combat

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotionedEvent
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.COMBAT
import cn.enaium.vac.client.module.Mod
import cn.enaium.vac.client.setting.*
import cn.enaium.vac.client.util.RotationUtil
import cn.enaium.vac.client.util.TargetUtil
import net.minecraft.entity.Entity
import net.minecraft.entity.ExperienceOrbEntity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.text.TranslatableText
import net.minecraft.util.Hand
import org.lwjgl.glfw.GLFW
import java.util.stream.Stream
import java.util.stream.StreamSupport

/**
 * @author Enaium
 */
@Module("Aura", key = GLFW.GLFW_KEY_R, type = COMBAT)
class Aura {

    @Setting("Range")
    private val range = FloatSetting(4.1f, 0.1f, 7.0f)

    @Setting("Priority")
    private val priority = ModeSetting("Distance", arrayListOf("Distance", "Fov", "Angle", "Health"))

    @Setting("Aim", description = "Auto Aim")
    private val aim = EnableSetting(false)

    @Setting("NoAttack")
    private val noAttack =
        EntityListSetting(
            arrayListOf(
                "entities/cat",
                "entities/dolphin",
                "entities/enderman",
                "entities/endermite",
                "entities/fox",
                "entities/iron_golem",
                "entities/item",
                "entities/item_frame",
                "entities/painting",
                "entities/piglin",
                "entities/player",
                "entities/snow_golem",
                "entities/strider",
                "entities/village",
                "entities/wolf",
                "entities/zombified_piglin",
                "entities/area_effect_cloud",
                "entities/evoker_fangs",
                "entities/leash_knot",
                "entities/experience_orb",
                "entities/fishing_bobber",
                "entities/falling_block",
                "entities/llama_spit"
            )
        )

    private var target: Entity? = null

    @Event
    fun onMotion(motioningEvent: MotioningEvent) {
        CF4M.MODULE.getByInstance(this).getExtend<Mod>().tag = priority.current

        if (mc.player!!.getAttackCooldownProgress(0f) < 1) return

        target = when (priority.current) {
            "Distance" -> getTargets().min(Comparator.comparingDouble { mc.player!!.squaredDistanceTo(it) })
                .orElse(null)
            "Fov" -> getTargets().min(Comparator.comparingDouble {
                RotationUtil.getDistanceBetweenAngles(it.boundingBox.center)
            }).orElse(null)
            "Angle" -> getTargets().min(Comparator.comparingDouble {
                RotationUtil.getAngleToLookVec(it.boundingBox.center)
            }).orElse(null)
            else -> getTargets().min(Comparator.comparingDouble {
                if (it is LivingEntity) {
                    it.health.toDouble()
                } else {
                    Double.MAX_VALUE
                }
            }).orElse(null)
        }

        if (aim.enable) {
            motioningEvent.yaw =
                RotationUtil.getNeededRotations(RotationUtil.getRandomCenter(target!!.boundingBox)).yaw
            motioningEvent.pitch =
                RotationUtil.getNeededRotations(RotationUtil.getRandomCenter(target!!.boundingBox)).pitch
        }
    }

    @Event
    fun onMotion(motionedEvent: MotionedEvent) {
        if (target == null) return

        mc.interactionManager!!.attackEntity(mc.player, target)
        mc.player!!.swingHand(Hand.MAIN_HAND)
        target = null
    }

    private fun getTargets(): Stream<Entity> {
        return StreamSupport.stream(TargetUtil.getEntities().spliterator(), true).filter {
            TargetUtil.isTarget(it)
        }.filter { !noAttack.all.contains(it.type.lootTableId.path) }
            .filter { mc.player!!.squaredDistanceTo(it) <= (range.current * range.current) }
    }
}