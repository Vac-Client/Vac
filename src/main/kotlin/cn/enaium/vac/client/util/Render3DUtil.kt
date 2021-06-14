package cn.enaium.vac.client.util

import cn.enaium.vac.client.mc
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gl.VertexBuffer
import net.minecraft.client.render.*
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import org.lwjgl.opengl.GL11
import java.awt.Color
import java.awt.Color.*


object Render3DUtil {

    fun getCameraPos(): Vec3d {
        return mc.gameRenderer.camera.pos
    }


    fun drawSolid(bb: Box, vertexBuffer: VertexBuffer) {
        val bufferBuilder = Tessellator.getInstance().buffer
        bufferBuilder.begin(
            VertexFormat.DrawMode.QUADS,
            VertexFormats.POSITION
        )
        drawSolid(bb, bufferBuilder)
        bufferBuilder.end()
        vertexBuffer.upload(bufferBuilder)
    }

    fun drawSolid(bb: Box, bufferBuilder: BufferBuilder) {
        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next()

        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next()

        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next()

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next()
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next()

        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next()
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next()

        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next()
    }


    fun drawOutlined(bb: Box, vertexBuffer: VertexBuffer) {
        val bufferBuilder = Tessellator.getInstance().buffer
        bufferBuilder.begin(
            VertexFormat.DrawMode.DEBUG_LINES,
            VertexFormats.POSITION
        )
        drawOutline(bb, bufferBuilder)
        bufferBuilder.end()
        vertexBuffer.upload(bufferBuilder)
    }

    fun drawOutline(bb: Box, bufferBuilder: BufferBuilder) {
        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next()

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next()

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next()

        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next()

        bufferBuilder.vertex(bb.minX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next()

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next()

        bufferBuilder.vertex(bb.maxX, bb.minY, bb.maxZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next()

        bufferBuilder.vertex(bb.minX, bb.minY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next()

        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next()

        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.minZ).next()
        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next()

        bufferBuilder.vertex(bb.maxX, bb.maxY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next()

        bufferBuilder.vertex(bb.minX, bb.maxY, bb.maxZ).next()
        bufferBuilder.vertex(bb.minX, bb.maxY, bb.minZ).next()
    }

    fun drawBox(matrixStack: MatrixStack, vertexBuffer: VertexBuffer, box: Box, color: Color) {
        matrixStack.push()
        val regionX: Int = (getCameraPos().getX().toInt() shr 9) * 512
        val regionZ: Int = (getCameraPos().getZ().toInt() shr 9) * 512
        RenderSystem.setShader(GameRenderer::getPositionShader)
        matrixStack.translate(
            box.minX - regionX, box.minY,
            box.minZ - regionZ
        )
        matrixStack.scale(
            (box.maxX - box.minX).toFloat(),
            (box.maxY - box.minY).toFloat(), (box.maxZ - box.minZ).toFloat()
        )

        val r = color.red.toFloat() / 255.0f
        val g = color.green.toFloat() / 255.0f
        val b = color.blue.toFloat() / 255.0f
        val a = color.alpha.toFloat() / 255.0f
        RenderSystem.setShaderColor(r, g, b, a)

        val viewMatrix = matrixStack.peek().model
        val projMatrix = RenderSystem.getProjectionMatrix()
        val shader = RenderSystem.getShader()


        vertexBuffer.setShader(viewMatrix, projMatrix, shader)
        matrixStack.pop()
    }

    fun applyRegionalRenderOffset(matrixStack: MatrixStack) {
        val camPos = getCameraPos()
        val blockPos: BlockPos = mc.blockEntityRenderDispatcher.camera.blockPos
        val regionX = (blockPos.x shr 9) * 512
        val regionZ = (blockPos.z shr 9) * 512

        matrixStack.translate(
            regionX - camPos.x, -camPos.y,
            regionZ - camPos.z
        )
    }

    fun settings(matrixStack: MatrixStack) {
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glEnable(GL11.GL_LINE_SMOOTH)
        GL11.glEnable(GL11.GL_CULL_FACE)
        GL11.glDisable(GL11.GL_DEPTH_TEST)
        matrixStack.push()
        applyRegionalRenderOffset(matrixStack)
    }

    fun resets(matrixStack: MatrixStack) {
        matrixStack.pop();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        GL11.glEnable(GL11.GL_DEPTH_TEST)
        GL11.glDisable(GL11.GL_BLEND)
        GL11.glDisable(GL11.GL_LINE_SMOOTH)
    }
}