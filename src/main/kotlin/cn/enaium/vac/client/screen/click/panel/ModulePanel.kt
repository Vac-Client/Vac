package cn.enaium.vac.client.screen.click.panel

import cn.enaium.cf4m.provider.ModuleProvider
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.screen.click.getMaxModule
import cn.enaium.vac.client.screen.click.panel.setting.*
import cn.enaium.vac.client.setting.*
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.gui.Drawable
import net.minecraft.client.gui.Element
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

/**
 * @author Enaium
 */
class ModulePanel(private val module: ModuleProvider, var x: Int, var y: Int) : Drawable, Element {

    private var hovered = false

    private var settingPanel = false

    private val settingList = ArrayList<SettingPanel>();

    init {
        for (settingProvider in module.setting.all) {
            when (settingProvider.getSetting<Any>()) {
                is EnableSetting -> {
                    settingList.add(EnableSettingPanel(settingProvider))
                }
                is IntegerSetting, is FloatSetting, is DoubleSetting, is LongSetting, is StringSetting -> {
                    settingList.add(ValueSettingPanel(settingProvider))
                }
                is ModeSetting -> {
                    settingList.add(ModeSettingPanel(settingProvider))
                }
                else -> settingList.add(ActionSettingPanel(settingProvider))
            }
        }
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        val height = FontUtil.height + 10
        hovered = Render2DUtil.isHovered(mouseX, mouseY, x, y, getMaxModule(), height)
        Render2DUtil.drawRectWH(
            matrices,
            x,
            y,
            getMaxModule(),
            height,
            if (hovered) Color(0, 255, 255).rgb else Color(0, 0, 255).rgb
        )
        FontUtil.drawHVCenteredString(
            matrices,
            module.name,
            x + getMaxModule() / 2,
            y + height / 2,
            if (module.enable) Color(255, 0, 255).rgb else 0xFFFFFF
        )

        if (hovered) {
            FontUtil.draw(
                matrices,
                module.description,
                Render2DUtil.scaledWidth - FontUtil.getWidth(module.description),
                Render2DUtil.scaledHeight - FontUtil.height
            )
        }

        if (settingPanel) {
            var settingY = y
            settingList.forEach {
                it.x = x + getMaxModule()
                it.y = settingY
                it.render(matrices, mouseX, mouseY, delta)
                settingY += FontUtil.height + 10
            }
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button == 0 && hovered) {
            module.enable()
        } else if (button == 1 && hovered) {
            settingPanel = !settingPanel
        }

        if (settingPanel) {
            settingList.forEach { it.mouseClicked(mouseX, mouseY, button) }
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }
}