package cn.enaium.vac.client.screen.widget

import cn.enaium.vac.client.util.FontUtil
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EntityType
import net.minecraft.text.TranslatableText

/**
 * @author Enaium
 */
class EntityListWidget {
    class Entry(val entityType: EntityType<*>) : ListWidget.Entry<Entry>() {
        override fun render(
            matrices: MatrixStack,
            index: Int,
            y: Int,
            x: Int,
            entryWidth: Int,
            entryHeight: Int,
            mouseX: Int,
            mouseY: Int,
            hovered: Boolean,
            tickDelta: Float
        ) {
            FontUtil.draw(
                matrices,
                TranslatableText(entityType.translationKey).string,
                x,
                y + FontUtil.height
            )

            FontUtil.draw(
                matrices, entityType.lootTableId.path,
                x,
                y
            )
            super.render(matrices, index, y, x, entryWidth, entryHeight, mouseX, mouseY, hovered, tickDelta)
        }
    }
}