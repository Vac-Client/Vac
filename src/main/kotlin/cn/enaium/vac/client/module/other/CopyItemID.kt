package cn.enaium.vac.client.module.other

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.KeyboardEvent
import cn.enaium.vac.client.event.RenderTooltipEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.OTHER
import cn.enaium.vac.client.util.ChatUtil.success
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.text.LiteralText
import net.minecraft.util.Formatting
import org.lwjgl.glfw.GLFW

/**
 * @author Enaium
 */
@Module("CopyItemID", type = OTHER)
class CopyItemID {

    private var id: String? = null

    @Event
    fun renderTooltip(renderTooltipEvent: RenderTooltipEvent) {
        renderTooltipEvent.tooltip.add(LiteralText(Formatting.LIGHT_PURPLE.toString() + "Press C Copy ID"))
        id = renderTooltipEvent.stack.item.toString() //to ID
    }

    @Event
    fun onKey(keyboardEvent: KeyboardEvent) {
        if (keyboardEvent.key == GLFW.GLFW_KEY_C && id != null && mc.currentScreen is InventoryScreen) {
            MinecraftClient.getInstance().keyboard.clipboard = id
            success("Copy:$id")
        }
    }
}