package cn.enaium.vac.client.screen.design

import cn.enaium.cf4m.CF4M
import cn.enaium.vac.client.module.ui.UI
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import java.awt.Color

/**
 * @author Enaium
 */
class Design : Screen(LiteralText("")) {

    private val panel = ArrayList<Panel>()

    override fun init() {
        panel.clear()
        val filter = CF4M.MODULE.all.filter { it.instance is UI }
        filter.forEach { panel.add(Panel(it.instance as UI)) }
        addDrawable(ButtonWidget(width - 50, height - 20, 50, 20, LiteralText("Reset")) {
            filter.forEach {
                val ui = it.instance as UI
                ui.x = ui.defaultX
                ui.y = ui.defaultY
                ui.moved = false
            }
        })
        super.init()
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        panel.forEach { it.render(matrices, mouseX, mouseY, delta) }
        super.render(matrices, mouseX, mouseY, delta)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        panel.forEach { it.mouseClicked(mouseX, mouseY, button) }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        panel.forEach { it.mouseReleased(mouseX, mouseY, button) }
        return super.mouseReleased(mouseX, mouseY, button)
    }
}