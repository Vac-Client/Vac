package cn.enaium.vac.client.screen.click.panel

import cn.enaium.cf4m.CF4M
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.screen.click.getMaxModule
import cn.enaium.vac.client.screen.click.getMaxType
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.gui.Drawable
import net.minecraft.client.gui.Element
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

/**
 * @author Enaium
 */
class TypePanel(private val type: String, private var x: Int, private var y: Int) : Drawable, Element {

    private var dragging = false
    private var hovered = false

    private var tempX = 0
    private var tempY = 0

    private val moduleList = ArrayList<ModulePanel>()

    private var modulePanel = false

    init {
        moduleList.clear()
        for (moduleProvider in CF4M.MODULE.getAllByType(type)) {
            moduleList.add(ModulePanel(moduleProvider, 0, 0))
        }
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        val height = FontUtil.height + 10

        hovered = Render2DUtil.isHovered(mouseX, mouseY, x, y, getMaxType() + 20, height)

        if (dragging) {
            x = mouseX - tempX
            y = mouseY - tempY
        }

        Render2DUtil.drawRectWH(matrices, x, y, getMaxType(), height, Color(40, 0, 255).rgb)
        FontUtil.drawHVCenteredString(matrices, type, x + getMaxType() / 2, y + height / 2)

        if (modulePanel) {
            var moduleY = y + height
            moduleList.forEach {
                it.x = x + getMaxType() / 2 - getMaxModule() / 2
                it.y = moduleY
                it.render(matrices, mouseX, mouseY, delta)
                moduleY += FontUtil.height + 5
            }
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button == 0 && hovered) {
            tempX = mouseX.toInt() - x
            tempY = mouseY.toInt() - y
            dragging = true
        } else if (button == 1 && hovered) {
            modulePanel = !modulePanel
        }

        if (modulePanel) {
            moduleList.forEach { it.mouseClicked(mouseX, mouseY, button) }
        }

        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        dragging = false
        return super.mouseReleased(mouseX, mouseY, button)
    }
}