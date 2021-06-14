package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.setting.StringSetting
import net.minecraft.client.gui.screen.DeathScreen

/**
 * @author Enaium
 */
@Module("AutoSign", type = PLAYER)
class AutoSign {
    @Setting("text", description = "/n wrap")
    val text = StringSetting("Vac Client/nByEnaium")
}