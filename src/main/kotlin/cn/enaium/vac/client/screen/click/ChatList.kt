package cn.enaium.vac.client.screen.click

import cn.enaium.cf4m.CF4M
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.screen.widget.ChatListWidget
import cn.enaium.vac.client.screen.widget.ListWidget
import cn.enaium.vac.client.setting.ChatListSetting
import cn.enaium.vac.client.util.KeyboardUtil
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextFieldWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText

/**
 * @author Enaium
 */
class ChatList(private val chatListSetting: ChatListSetting) : Screen(LiteralText("")) {
    private lateinit var entryListWidget: ListWidget<ChatListWidget.Entry>
    private lateinit var removeButton: ButtonWidget
    private lateinit var keyTextField: TextFieldWidget
    private lateinit var chatTextField: TextFieldWidget

    override fun init() {
        entryListWidget = ListWidget(mc, width, height, 50, height - 50, 24)
        chatListSetting.all.forEach { entryListWidget.addEntry(ChatListWidget.Entry(it)) }

        keyTextField = TextFieldWidget(mc.textRenderer, 5, height - 30, 20, 20, LiteralText(""))
        chatTextField = TextFieldWidget(mc.textRenderer, 35, height - 30, 100, 20, LiteralText(""))

        addButton(ButtonWidget(5, 5, 50, 20, LiteralText("Add")) {
            if (keyTextField.text.isNotEmpty() && chatTextField.text.isNotEmpty()) {
                val key = KeyboardUtil.getKey(keyTextField.text)
                if (key != -1) {
                    chatListSetting.all.add(Pair(key, chatTextField.text))
                } else {
                    CF4M.CONFIGURATION.command.message("Key:${keyTextField.text} Not Found")
                }
            }
        })
        removeButton = ButtonWidget(width - 55, 5, 50, 20, LiteralText("Remove")) {
            entryListWidget.children().remove(entryListWidget.selected)
            chatListSetting.all.remove(entryListWidget.selected!!.pair)
        }

        addButton(removeButton)
        addChild(keyTextField)
        addChild(chatTextField)
        super.init()
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        removeButton.active = entryListWidget.selected != null
        entryListWidget.render(matrices, mouseX, mouseY, delta)
        chatTextField.render(matrices, mouseX, mouseY, delta)
        keyTextField.render(matrices, mouseX, mouseY, delta)
        super.render(matrices, mouseX, mouseY, delta)
    }
}