package cn.enaium.vac.client.screen.click

import cn.enaium.vac.client.mc
import cn.enaium.vac.client.screen.widget.EntityListWidget
import cn.enaium.vac.client.screen.widget.ListWidget
import cn.enaium.vac.client.setting.EntityListSetting
import cn.enaium.vac.client.setting.ItemListSetting
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

/**
 * @author Enaium
 */
class EntityList(val entityListSetting: EntityListSetting) : Screen(LiteralText("")) {
    private lateinit var entryListWidget: ListWidget<EntityListWidget.Entry>
    private lateinit var removeButton: ButtonWidget

    override fun init() {
        entryListWidget = ListWidget(mc, width, height, 50, height - 50, 24)
        entityListSetting.all.forEach { o1 ->
            Registry.ENTITY_TYPE.filter { it.lootTableId.path == o1 }.forEach {
                entryListWidget.addEntry(EntityListWidget.Entry(it))
            }
        }

        removeButton = ButtonWidget(width - 55, 5, 50, 20, LiteralText("Remove")) {
            entryListWidget.children().remove(entryListWidget.selectedOrNull)
            entityListSetting.all.remove(entryListWidget.selectedOrNull!!.entityType.lootTableId.path)
        }
        addDrawable(ButtonWidget(5, 5, 50, 20, LiteralText("All")) {
            mc.openScreen(EntityAllList(this))
        })
        addDrawable(entryListWidget)
        addDrawable(removeButton)
        super.init()
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        entryListWidget.render(matrices, mouseX, mouseY, delta)

        removeButton.active = entryListWidget.selectedOrNull != null

        super.render(matrices, mouseX, mouseY, delta)
    }
}