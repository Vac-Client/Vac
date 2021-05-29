package cn.enaium.vac.client.screen.click.panel.setting

import cn.enaium.cf4m.provider.SettingProvider
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.screen.click.getMaxSetting
import cn.enaium.vac.client.setting.ModeSetting
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

/**
 * @author Enaium
 */
class ModeSettingPanel(setting: SettingProvider) : SettingPanel(setting) {
    val set = setting.getSetting<ModeSetting>()!!

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        hovered = Render2DUtil.isHovered(mouseX, mouseY, x, y, getMaxSetting(), height)

        Render2DUtil.drawRectWH(matrices, x, y, getMaxSetting(), height, Color(0, 0, 255).rgb)
        FontUtil.drawHVCenteredString(
            matrices,
            "${setting.name}:${set.current}",
            x + getMaxSetting() / 2,
            y + height / 2
        )
        super.render(matrices, mouseX, mouseY, delta)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (hovered) {
            when (button) {
                0 -> {
                    try {
                        set.current = set.modes[set.getCurrentModeIndex() + 1]
                    } catch (e: Exception) {
                        set.current = set.modes.first()
                    }
                }
                else -> {
                    try {
                        set.current = set.modes[set.getCurrentModeIndex() - 1]
                    } catch (e: Exception) {
                        set.current = set.modes.last()
                    }
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }
}