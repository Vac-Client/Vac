package cn.enaium.vac.client.screen.widget

import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.util.KeyboardUtil
import net.minecraft.client.util.math.MatrixStack

/**
 * @author Enaium
 */
class ChatListWidget {
    class Entry(val pair: Pair<Int, String>) : ListWidget.Entry<Entry>() {
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
            FontUtil.draw(matrices, "Key:" + KeyboardUtil.getName(pair.first), x, y)
            FontUtil.draw(matrices, "Chat:" + pair.second, x, y + FontUtil.height)
            super.render(matrices, index, y, x, entryWidth, entryHeight, mouseX, mouseY, hovered, tickDelta)
        }
    }
}