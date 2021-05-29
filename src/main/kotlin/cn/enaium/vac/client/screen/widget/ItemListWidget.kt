package cn.enaium.vac.client.screen.widget

import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.mc
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

/**
 * @author Enaium
 */
class ItemListWidget {
    class Entry(val name: String) : ListWidget.Entry<Entry>() {
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
            val itemStack = ItemStack(Registry.ITEM.get(Identifier(name)))
            if (!itemStack.isEmpty) {
                mc.itemRenderer.renderGuiItemIcon(itemStack, x, y)
                FontUtil.draw(
                    matrices,
                    TranslatableText(itemStack.translationKey).string,
                    x + entryWidth - FontUtil.getWidth(TranslatableText(itemStack.translationKey).string),
                    y + FontUtil.height
                )
            }
            FontUtil.draw(matrices, name, x + entryWidth - FontUtil.getWidth(name), y)
            super.render(matrices, index, y, x, entryWidth, entryHeight, mouseX, mouseY, hovered, tickDelta)
        }
    }
}