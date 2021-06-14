package cn.enaium.vac.client.screen.click.panel.setting

import cn.enaium.cf4m.provider.SettingProvider
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.gui.Drawable
import net.minecraft.client.gui.Element
import net.minecraft.client.util.math.MatrixStack

/**
 * @author Enaium
 */
open class SettingPanel(val setting: SettingProvider, var x: Int = 0, var y: Int = 0) : Drawable, Element {

    val height = FontUtil.height + 5
    var hovered = false

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        if (hovered) {
            FontUtil.draw(
                matrices,
                setting.description,
                Render2DUtil.scaledWidth - FontUtil.getWidth(setting.description),
                Render2DUtil.scaledHeight - FontUtil.height
            )
        }
    }
}