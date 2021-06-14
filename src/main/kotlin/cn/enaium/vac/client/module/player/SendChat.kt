package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Enable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.KeyboardEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.setting.ChatListSetting
import cn.enaium.vac.client.setting.EnableSetting
import org.lwjgl.glfw.GLFW

/**
 * @author Enaium
 */
@Module("SendChat", description = "Press the key to send", type = PLAYER)
class SendChat {

    @Setting("ChatList")
    private val chatList = ChatListSetting(arrayListOf(Pair(GLFW.GLFW_KEY_H, "/spawn")))

    @Setting("OnScreen", description = "When the screen is open")
    private val onScreen = EnableSetting(false)

    @Event
    fun onKey(keyboardEvent: KeyboardEvent) {

        if (onScreen.enable && mc.currentScreen != null) return

        chatList.all.forEach {
            if (it.first == keyboardEvent.key) {
                mc.player!!.sendChatMessage(it.second)
            }
        }
    }
}