package cn.enaium.vac.client.screen.click.panel.setting

import cn.enaium.cf4m.provider.SettingProvider
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.screen.click.ChatList
import cn.enaium.vac.client.screen.click.EntityList
import cn.enaium.vac.client.screen.click.ItemList
import cn.enaium.vac.client.screen.click.getMaxSetting
import cn.enaium.vac.client.setting.BlockListSetting
import cn.enaium.vac.client.setting.ChatListSetting
import cn.enaium.vac.client.setting.EntityListSetting
import cn.enaium.vac.client.setting.ItemListSetting
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

/**
 * @author Enaium
 */
class ActionSettingPanel(setting: SettingProvider) : SettingPanel(setting) {
    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        hovered = Render2DUtil.isHovered(mouseX, mouseY, x, y, getMaxSetting(), height)
        Render2DUtil.drawRectWH(matrices, x, y, getMaxSetting(), height, Color(0, 0, 255).rgb)
        FontUtil.drawHVCenteredString(matrices, setting.name, x + getMaxSetting() / 2, y + height / 2)
        super.render(matrices, mouseX, mouseY, delta)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button == 0 && hovered) {
            when (val set = setting.getSetting<Any>()) {
                is ItemListSetting, is BlockListSetting -> {
                    mc.openScreen(ItemList(set as ItemListSetting))
                }
                is EntityListSetting -> {
                    mc.openScreen(EntityList(set))
                }
                is ChatListSetting -> {
                    mc.openScreen(ChatList(set))
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }
}