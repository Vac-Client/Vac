package cn.enaium.vac.client.module.ui

import net.minecraft.client.gui.Drawable
import net.minecraft.client.util.math.MatrixStack

/**
 * @author Enaium
 */
open class UI(var defaultX: Int = 0, var defaultY: Int = 0) {
    var x = 0
    var y = 0
    var width = 0
    var height = 0
    var moved = false

    open fun render(matrices: MatrixStack) {
        if (!moved) {
            x = defaultX
            y = defaultY
        }
    }
}