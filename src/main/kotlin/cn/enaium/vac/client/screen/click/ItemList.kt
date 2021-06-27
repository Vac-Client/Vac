package cn.enaium.vac.client.screen.click

import cn.enaium.vac.client.mc
import cn.enaium.vac.client.screen.widget.ItemListWidget
import cn.enaium.vac.client.screen.widget.ListWidget
import cn.enaium.vac.client.setting.BlockEntityListSetting
import cn.enaium.vac.client.setting.BlockListSetting
import cn.enaium.vac.client.setting.ItemListSetting
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextFieldWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import net.minecraft.util.Identifier
import net.minecraft.util.InvalidIdentifierException
import net.minecraft.util.registry.Registry

/**
 * @author Enaium
 */
class ItemList(val itemListSetting: ItemListSetting) : Screen(LiteralText("")) {
    private lateinit var entryListWidget: ListWidget<ItemListWidget.Entry>
    private lateinit var removeButton: ButtonWidget
    private lateinit var textFieldWidget: TextFieldWidget

    override fun init() {
        entryListWidget = ListWidget(mc, width, height, 50, height - 50, 24)
        itemListSetting.all.forEach { entryListWidget.addEntry(ItemListWidget.Entry(it)) }


        removeButton = ButtonWidget(width - 55, 5, 50, 20, LiteralText("Remove")) {
            entryListWidget.children().remove(entryListWidget.selectedOrNull)
            itemListSetting.all.remove(entryListWidget.selectedOrNull!!.name)
        }

        textFieldWidget = TextFieldWidget(mc.textRenderer, 5, height - 30, 50, 20, LiteralText(""))
        addDrawableChild(entryListWidget)
        addDrawableChild(ButtonWidget(60, height - 30, 50, 20, LiteralText("All")) {
            mc.openScreen(
                ItemAllList(
                    this,
                    when (itemListSetting) {
                        is BlockListSetting -> ItemAllList.Type.BLOCK
                        is BlockEntityListSetting -> ItemAllList.Type.BLOCK_ENTITY
                        else -> ItemAllList.Type.ITEM
                    }
                )
            )

        })
        addDrawableChild(ButtonWidget(5, 5, 50, 20, LiteralText("Add")) {
            try {
                var clipboard = mc.keyboard.clipboard.lowercase()
                if (Registry.ITEM.containsId(Identifier(clipboard)) && textFieldWidget.text.isEmpty()) {
                    if (!clipboard.startsWith("minecraft:")) {
                        clipboard = "minecraft:$clipboard"
                    }

                    entryListWidget.addEntry(ItemListWidget.Entry(clipboard))
                    itemListSetting.all.add(clipboard)
                } else {
                    addByTextField()
                }
            } catch (e: InvalidIdentifierException) {
                addByTextField()
            }
        })

        addDrawableChild(removeButton)
        addDrawableChild(textFieldWidget)
        super.init()
    }

    private fun addByTextField() {
        var lowercase = textFieldWidget.text.lowercase()

        if (!lowercase.startsWith("minecraft:")) {
            lowercase = "minecraft:$lowercase"
        }

        if (Registry.ITEM.containsId(Identifier(lowercase))) {
            entryListWidget.addEntry(ItemListWidget.Entry(lowercase))
            itemListSetting.all.add(lowercase)
        }
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        removeButton.active = entryListWidget.selectedOrNull != null
        entryListWidget.render(matrices, mouseX, mouseY, delta)
        textFieldWidget.render(matrices, mouseX, mouseY, delta)
        super.render(matrices, mouseX, mouseY, delta)
    }
}