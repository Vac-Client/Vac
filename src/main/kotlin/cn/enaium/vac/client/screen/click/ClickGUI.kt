package cn.enaium.vac.client.screen.click

import cn.enaium.cf4m.CF4M
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.screen.click.panel.TypePanel
import cn.enaium.vac.client.screen.design.Design
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.LiteralText
import net.minecraft.client.util.math.MatrixStack

/**
 * @author Enaium
 */
class ClickGUI : Screen(LiteralText("")) {

    private val typeList = ArrayList<TypePanel>()

    init {
        typeList.clear()
        var y = 5
        for (s in CF4M.MODULE.allType) {
            typeList.add(TypePanel(s, 5, y))
            y += FontUtil.height + 20
        }
        super.init()
    }

    override fun init() {
        addButton(ButtonWidget(0, height - 20, 50, 20, LiteralText("Design")) {
            mc.openScreen(Design())
        })
        super.init()
    }


    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        typeList.forEach { it.render(matrices, mouseX, mouseY, delta) }
        super.render(matrices, mouseX, mouseY, delta)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        typeList.forEach { it.mouseClicked(mouseX, mouseY, button) }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        typeList.forEach { it.mouseReleased(mouseX, mouseY, button) }
        return super.mouseReleased(mouseX, mouseY, button)
    }

    override fun isPauseScreen(): Boolean {
        return false
    }
}

fun getMaxType(): Int {
    val allType = CF4M.MODULE.allType
    allType.sortByDescending { FontUtil.getWidth(it) }
    return FontUtil.getWidth(allType[0]) + 20
}

fun getMaxModule(): Int {
    val all = CF4M.MODULE.all
    all.sortByDescending { FontUtil.getWidth(it.name) }
    return FontUtil.getWidth(all[0].name)
}

fun getMaxSetting(): Int {
    val allSetting = ArrayList<String>()
    CF4M.MODULE.all.forEach { module -> module.setting.all.forEach { allSetting.add(it.name) } }
    allSetting.sortByDescending { FontUtil.getWidth(it) }
    return FontUtil.getWidth(allSetting[0])
}