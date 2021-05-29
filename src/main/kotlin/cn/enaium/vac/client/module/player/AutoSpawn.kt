package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Enable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.setting.EnableSetting
import cn.enaium.vac.client.setting.IntegerSetting
import cn.enaium.vac.client.setting.StringSetting
import net.minecraft.client.gui.screen.DeathScreen

/**
 * @author Enaium
 */
@Module("AutoSpawn", type = PLAYER)
class AutoSpawn {

    @Setting("CloseDeath", description = "Close death screen")
    private val close = EnableSetting(true)

    @Setting("Y", description = "Y is below 0")
    private val y = EnableSetting(true)

    @Setting("Command")
    private val command = StringSetting("/spawn")

    @Event
    fun closeDeath(motioningEvent: MotioningEvent) {
        if (!close.enable) {
            return
        }

        if (mc.currentScreen is DeathScreen) {
            mc.player!!.requestRespawn()
            mc.openScreen(null)
        }
    }

    @Event
    fun y(motioningEvent: MotioningEvent) {
        if (!y.enable) {
            return
        }

        if (mc.player!!.blockPos.y < 0) {
            mc.player!!.sendChatMessage(command.current)
        }
    }
}