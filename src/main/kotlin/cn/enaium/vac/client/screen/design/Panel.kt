package cn.enaium.vac.client.screen.design

import cn.enaium.vac.client.module.ui.UI
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.gui.Drawable
import net.minecraft.client.gui.Element
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

/**
 * @author Enaium
 */
class Panel(private val ui: UI) : Drawable, Element {

    private var dragging = false
    private var hovered = false

    private var tempX = 0
    private var tempY = 0

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        hovered = Render2DUtil.isHovered(mouseX, mouseY, ui.x, ui.y, ui.width, ui.height)


        Render2DUtil.drawRectWH(
            matrices,
            ui.x,
            ui.y,
            ui.width,
            ui.height,
            if (hovered) Color(100, 117, 189, 100).rgb else Color(100, 17, 189, 100).rgb
        )

        if (dragging) {
            ui.x = mouseX - tempX
            ui.y = mouseY - tempY
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button == 0 && hovered) {
            tempX = mouseX.toInt() - ui.x
            tempY = mouseY.toInt() - ui.y
            ui.moved = true
            dragging = true
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        dragging = false
        return super.mouseReleased(mouseX, mouseY, button)
    }
}