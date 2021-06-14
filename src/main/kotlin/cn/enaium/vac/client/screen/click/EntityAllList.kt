package cn.enaium.vac.client.screen.click

import cn.enaium.vac.client.mc
import cn.enaium.vac.client.screen.widget.EntityListWidget
import cn.enaium.vac.client.screen.widget.ListWidget
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import net.minecraft.util.registry.Registry

/**
 * @author Enaium
 */
class EntityAllList(private val entityList: EntityList) : Screen(LiteralText("")) {
    private lateinit var entryListWidget: ListWidget<EntityListWidget.Entry>
    private lateinit var buttonWidget: ButtonWidget

    override fun init() {
        entryListWidget = ListWidget(mc, width, height, 50, height - 50, 24)

        Registry.ENTITY_TYPE.forEach {
            entryListWidget.addEntry(EntityListWidget.Entry(it))
        }

        buttonWidget = ButtonWidget(width / 2 - 25, height - 30, 50, 20, LiteralText("Add")) {
            entityList.entityListSetting.all.add(entryListWidget.selectedOrNull!!.entityType.lootTableId.path)
            mc.openScreen(entityList)
        }

        addDrawable(entryListWidget)
        addDrawable(buttonWidget)
        super.init()
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        entryListWidget.render(matrices, mouseX, mouseY, delta)
        buttonWidget.active = entryListWidget.selectedOrNull != null
        super.render(matrices, mouseX, mouseY, delta)
    }
}