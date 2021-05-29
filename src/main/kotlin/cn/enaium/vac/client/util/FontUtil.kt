package cn.enaium.vac.client.util

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText

/**
 * @author Enaium
 */
object FontUtil {
    var tr = MinecraftClient.getInstance().textRenderer!!

    /**
     * Draw Text.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun draw(matrixStack: MatrixStack, text: String, x: Int, y: Int, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x.toFloat(), y.toFloat(), color)
    }

    /**
     * Draw Text.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun draw(matrixStack: MatrixStack, text: String, x: Float, y: Float, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x, y, color)
    }

    /**
     * Draw Text.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun draw(matrixStack: MatrixStack, text: String, x: Double, y: Double, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x.toFloat(), y.toFloat(), color)
    }

    /**
     * Draw Shadow Text.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawWithShadow(matrixStack: MatrixStack, text: String, x: Int, y: Int, color: Int = 0xFFFFFF): Int {
        return tr.drawWithShadow(matrixStack, text, x.toFloat(), y.toFloat(), color)
    }

    /**
     * Draw Shadow Text.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawWithShadow(matrixStack: MatrixStack, text: String, x: Double, y: Double, color: Int = 0xFFFFFF): Int {
        return tr.drawWithShadow(matrixStack, text, x.toFloat(), y.toFloat(), color)
    }

    /**
     * Draw Shadow Text.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawWithShadow(matrixStack: MatrixStack, text: String, x: Float, y: Float, color: Int = 0xFFFFFF): Int {
        return tr.drawWithShadow(matrixStack, text, x, y, color)
    }

    /**
     * Draw Text. Horizontal Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHCentered(matrixStack: MatrixStack, text: String, x: Int, y: Int, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x - tr.getWidth(LiteralText(text)) / 2.toFloat(), y.toFloat(), color)
    }

    /**
     * Draw Text. Horizontal Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHCenteredWithShadow(
        matrixStack: MatrixStack,
        text: String,
        x: Int,
        y: Int,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(
            matrixStack,
            text,
            x - tr.getWidth(LiteralText(text)) / 2.toFloat(),
            y.toFloat(),
            color
        )
    }

    /**
     * Draw Text. Horizontal Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHCentered(matrixStack: MatrixStack, text: String, x: Double, y: Double, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x.toFloat() - tr.getWidth(LiteralText(text)) / 2, y.toFloat(), color)
    }

    /**
     * Draw Text. Horizontal Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHCenteredWithShadow(
        matrixStack: MatrixStack,
        text: String,
        x: Double,
        y: Double,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(
            matrixStack,
            text,
            x.toFloat() - tr.getWidth(LiteralText(text)) / 2,
            y.toFloat(),
            color
        )
    }

    /**
     * Draw Text. Horizontal Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHCentered(matrixStack: MatrixStack, text: String, x: Float, y: Float, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x, y - tr.fontHeight / 2, color)
    }

    /**
     * Draw Text. Horizontal Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHCenteredWithShadow(
        matrixStack: MatrixStack,
        text: String,
        x: Float,
        y: Float,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(matrixStack, text, x, y - tr.fontHeight / 2, color)
    }

    /**
     * Draw Text. Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawVCenteredString(matrixStack: MatrixStack, text: String, x: Int, y: Int, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x.toFloat(), y - tr.fontHeight / 2.toFloat(), color)
    }

    /**
     * Draw Text. Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawVCenteredWithShadowString(
        matrixStack: MatrixStack,
        text: String,
        x: Int,
        y: Int,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(matrixStack, text, x.toFloat(), y - tr.fontHeight / 2.toFloat(), color)
    }

    /**
     * Draw Text. Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawVCenteredString(matrixStack: MatrixStack, text: String, x: Double, y: Double, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x.toFloat(), y.toFloat() - tr.fontHeight / 2, color)
    }

    /**
     * Draw Text. Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawVCenteredWithShadowString(
        matrixStack: MatrixStack,
        text: String,
        x: Double,
        y: Double,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(matrixStack, text, x.toFloat(), y.toFloat() - tr.fontHeight / 2, color)
    }

    /**
     * Draw Text. Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawVCenteredString(matrixStack: MatrixStack, text: String, x: Float, y: Float, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x - tr.getWidth(LiteralText(text)) / 2, y - tr.fontHeight / 2, color)
    }

    /**
     * Draw Text. Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawVCenteredWithShadowString(
        matrixStack: MatrixStack,
        text: String,
        x: Float,
        y: Float,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(
            matrixStack,
            text,
            x - tr.getWidth(LiteralText(text)) / 2,
            y - tr.fontHeight / 2,
            color
        )
    }

    /**
     * Draw Text. Horizontal and Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHVCenteredString(matrixStack: MatrixStack, text: String, x: Int, y: Int, color: Int = 0xFFFFFF): Int {
        return tr.draw(
            matrixStack,
            text,
            x - tr.getWidth(LiteralText(text)) / 2.toFloat(),
            y - tr.fontHeight / 2.toFloat(),
            color
        )
    }

    /**
     * Draw Text. Horizontal and Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHVCenteredWithShadowString(
        matrixStack: MatrixStack,
        text: String,
        x: Int,
        y: Int,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(
            matrixStack,
            text,
            x - tr.getWidth(LiteralText(text)) / 2.toFloat(),
            y - tr.fontHeight / 2.toFloat(),
            color
        )
    }

    /**
     * Draw Text. Horizontal and Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHVCenteredString(matrixStack: MatrixStack, text: String, x: Double, y: Double, color: Int = 0xFFFFFF): Int {
        return tr.draw(
            matrixStack,
            text,
            x.toFloat() - tr.getWidth(LiteralText(text)) / 2,
            y.toFloat() - tr.fontHeight / 2,
            color
        )
    }

    /**
     * Draw Text. Horizontal and Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHVCenteredWithShadowString(
        matrixStack: MatrixStack,
        text: String,
        x: Double,
        y: Double,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(
            matrixStack,
            text,
            x.toFloat() - tr.getWidth(LiteralText(text)) / 2,
            y.toFloat() - tr.fontHeight / 2,
            color
        )
    }

    /**
     * Draw Text. Horizontal and Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHVCenteredString(matrixStack: MatrixStack, text: String, x: Float, y: Float, color: Int = 0xFFFFFF): Int {
        return tr.draw(matrixStack, text, x - tr.getWidth(LiteralText(text)) / 2, y - tr.fontHeight / 2, color)
    }

    /**
     * Draw Text. Horizontal and Vertical Center.
     * @param matrixStack MatrixStack
     * @param text Text
     * @param x X
     * @param y Y
     * @param color Color
     * @return Layer width
     */
    fun drawHVCenteredWithShadowString(
        matrixStack: MatrixStack,
        text: String,
        x: Float,
        y: Float,
        color: Int = 0xFFFFFF
    ): Int {
        return tr.drawWithShadow(
            matrixStack,
            text,
            x - tr.getWidth(LiteralText(text)) / 2,
            y - tr.fontHeight / 2,
            color
        )
    }

    /**
     * Text Width
     * @param text Text
     */
    fun getWidth(text: String): Int {
        return tr.getWidth(LiteralText(text))
    }

    val height: Int
        get() = tr.fontHeight
}