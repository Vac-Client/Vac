package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.cf4m.annotation.module.Disable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.Mod
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.setting.ModeSetting
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects


/**
 * @author Enaium
 */
@Module("Bright", type = RENDER)
class Bright {

    @Setting("Mode")
    private val mode = ModeSetting("Gamma", arrayListOf("Gamma", "NightVision"))

    @Event
    fun onMotion(motioningEvent: MotioningEvent) {
        CF4M.MODULE.getByInstance(this).getExtend<Mod>().tag = mode.current
        when (mode.current) {
            "Gamma" -> mc.options.gamma = 300.0
            "NightVision" -> mc.player!!.addStatusEffect(
                StatusEffectInstance(
                    StatusEffects.NIGHT_VISION,
                    16360,
                    0,
                    false,
                    false
                )
            )
        }
    }

    @Disable
    fun onDisable() {
        mc.options.gamma = 1.0
        mc.player!!.removeStatusEffectInternal(StatusEffects.NIGHT_VISION)
    }
}