package cn.enaium.vac.client.screen.click

import cn.enaium.vac.client.mc
import cn.enaium.vac.client.screen.widget.ItemListWidget
import cn.enaium.vac.client.screen.widget.ListWidget
import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import net.minecraft.util.registry.Registry

/**
 * @author Enaium
 */
class ItemAllList(private val itemList: ItemList, private val type: Type) : Screen(LiteralText("")) {

    private lateinit var entryListWidget: ListWidget<ItemListWidget.Entry>
    private lateinit var buttonWidget: ButtonWidget

    override fun init() {
        entryListWidget = ListWidget(mc, width, height, 50, height - 50, 24)


        when (type) {
            Type.ITEM -> {
                Registry.ITEM.forEach {
                    entryListWidget.addEntry(ItemListWidget.Entry(it.asItem().toString()))
                }
            }
            Type.BLOCK -> {
                Registry.BLOCK.forEach {
                    entryListWidget.addEntry(ItemListWidget.Entry(it.asItem().toString()))
                }
            }

            else -> Registry.BLOCK_ENTITY_TYPE.ids.forEach {
                entryListWidget.addEntry(ItemListWidget.Entry(it.path))
            }
        }



        buttonWidget = ButtonWidget(width / 2 - 25, height - 30, 50, 20, LiteralText("Add")) {
            itemList.itemListSetting.all.add("minecraft:" + entryListWidget.selectedOrNull!!.name)
            mc.openScreen(itemList)
        }
        addDrawableChild(buttonWidget)
        addDrawableChild(entryListWidget)
        super.init()
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        buttonWidget.active = entryListWidget.selectedOrNull != null
        entryListWidget.render(matrices, mouseX, mouseY, delta)
        super.render(matrices, mouseX, mouseY, delta)
    }

    enum class Type {
        ITEM,
        BLOCK,
        BLOCK_ENTITY
    }
}