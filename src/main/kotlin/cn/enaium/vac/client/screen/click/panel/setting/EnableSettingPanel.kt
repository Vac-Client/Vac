package cn.enaium.vac.client.screen.click.panel.setting

import cn.enaium.cf4m.provider.SettingProvider
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.screen.click.getMaxSetting
import cn.enaium.vac.client.setting.EnableSetting
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

/**
 * @author Enaium
 */
class EnableSettingPanel(setting: SettingProvider) : SettingPanel(setting) {

    val set = setting.getSetting<EnableSetting>()!!


    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {

        hovered = Render2DUtil.isHovered(mouseX, mouseY, x, y, getMaxSetting(), height)

        val color = if (set.enable) {
            Color(0, 255, 0).rgb
        } else {
            Color(255, 0, 0).rgb
        }
        Render2DUtil.drawRectWH(matrices, x, y, getMaxSetting(), height, color)
        FontUtil.drawHVCenteredString(matrices, setting.name, x + getMaxSetting() / 2, y + height / 2)
        super.render(matrices, mouseX, mouseY, delta)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button == 0 && hovered) {
            set.enable = !set.enable
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }
}