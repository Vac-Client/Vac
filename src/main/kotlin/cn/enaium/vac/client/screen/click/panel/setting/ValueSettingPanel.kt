package cn.enaium.vac.client.screen.click.panel.setting

import cn.enaium.cf4m.provider.SettingProvider
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.screen.click.EditValue
import cn.enaium.vac.client.screen.click.getMaxSetting
import cn.enaium.vac.client.setting.DoubleSetting
import cn.enaium.vac.client.setting.FloatSetting
import cn.enaium.vac.client.setting.IntegerSetting
import cn.enaium.vac.client.setting.LongSetting
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

/**
 * @author Enaium
 */
class ValueSettingPanel(setting: SettingProvider) : SettingPanel(setting) {
    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        val name = when (val s = setting.getSetting<Any>()) {
            is FloatSetting -> "${setting.name}:${s.current}"
            is DoubleSetting -> "${setting.name}:${s.current}"
            is LongSetting -> "${setting.name}:${s.current}"
            is IntegerSetting -> "${setting.name}:${s.current}"
            else -> setting.name
        }

        hovered = Render2DUtil.isHovered(mouseX, mouseY, x, y, getMaxSetting(), height)
        Render2DUtil.drawRectWH(matrices, x, y, getMaxSetting(), height, Color(0, 0, 255).rgb)
        FontUtil.drawHVCenteredString(matrices, name, x + getMaxSetting() / 2, y + height / 2)
        super.render(matrices, mouseX, mouseY, delta)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (hovered && button == 0) {
            mc.openScreen(EditValue(setting))
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }
}