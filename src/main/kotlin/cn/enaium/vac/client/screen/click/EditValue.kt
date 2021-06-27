package cn.enaium.vac.client.screen.click

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.provider.SettingProvider
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.setting.*
import cn.enaium.vac.client.util.ChatUtil
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ScreenTexts
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextFieldWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import net.minecraft.text.TranslatableText

/**
 * @author Enaium
 */
class EditValue(val setting: SettingProvider) : Screen(LiteralText("")) {

    private lateinit var textFieldWidget: TextFieldWidget

    override fun init() {
        textFieldWidget =
            TextFieldWidget(mc.textRenderer, width / 2 - 25, height / 2 - 10, 50, 20, LiteralText(""))
        addDrawableChild(ButtonWidget(width / 2 - 25, height / 2 + 20, 50, 20, ScreenTexts.DONE) {
            try {
                when (val s = setting.getSetting<Any>()) {
                    is FloatSetting -> {
                        val toFloat = textFieldWidget.text.toFloat()
                        if (toFloat >= s.min && toFloat <= s.max) {
                            s.current = toFloat
                        }
                    }
                    is DoubleSetting -> {
                        val toDouble = textFieldWidget.text.toDouble()
                        if (toDouble >= s.min && toDouble <= s.max) {
                            s.current = toDouble
                        }
                    }
                    is LongSetting -> {
                        val toLong = textFieldWidget.text.toLong()
                        if (toLong >= s.min && toLong <= s.max) {
                            s.current = toLong
                        }
                    }
                    is IntegerSetting -> {
                        val toInt = textFieldWidget.text.toInt()
                        if (toInt >= s.min && toInt <= s.max) {
                            s.current = toInt
                        }
                    }
                    is StringSetting -> s.current = textFieldWidget.text.toString()
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
                ChatUtil.error(e.localizedMessage)
            }
            mc.openScreen(ClickGUI())
        })
        addDrawableChild(textFieldWidget)
        super.init()
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        textFieldWidget.render(matrices, mouseX, mouseY, delta)
        super.render(matrices, mouseX, mouseY, delta)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        textFieldWidget.mouseClicked(mouseX, mouseY, button)
        return super.mouseClicked(mouseX, mouseY, button)
    }
}