package cn.enaium.vac.client.screen.widget

import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.widget.EntryListWidget
import net.minecraft.client.util.math.MatrixStack

/**
 * @author Enaium
 */
class ListWidget<T : ListWidget.Entry<T>>(
    client: MinecraftClient,
    width: Int,
    height: Int,
    top: Int,
    bottom: Int,
    itemHeight: Int
) : EntryListWidget<T>(client, width, height, top, bottom, itemHeight) {
    private fun setSelectedItem(index: Int) {
        if (index == -1) {
            selected = null
        } else if (super.getEntryCount() != 0) {
            selected = getEntry(index)
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        for ((index, child) in children().withIndex()) {
            if (child.hovered) {
                setSelectedItem(index)
            }
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    public override fun addEntry(entry: T): Int {
        return super.addEntry(entry)
    }

    open class Entry<E : EntryListWidget.Entry<E>> : EntryListWidget.Entry<E>() {
        var hovered = false
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
            this.hovered = hovered
        }
    }
}