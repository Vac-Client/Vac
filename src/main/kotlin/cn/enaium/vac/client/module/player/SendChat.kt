package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Enable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.KeyboardEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.setting.ChatListSetting
import org.lwjgl.glfw.GLFW

/**
 * @author Enaium
 */
@Module("SendChat", type = PLAYER)
class SendChat {
    @Setting("ChatList")
    private val chatList = ChatListSetting(arrayListOf(Pair(GLFW.GLFW_KEY_H, "/spawn")))

    @Event
    fun onKey(keyboardEvent: KeyboardEvent) {
        if (mc.currentScreen != null) return

        chatList.all.forEach {
            if (it.first == keyboardEvent.key) {
                mc.player!!.sendChatMessage(it.second)
            }
        }
    }
}