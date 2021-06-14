package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.CF4M
import cn.enaium.vac.client.service.RenderService
import cn.enaium.vac.client.util.Render3DUtil
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gl.VertexBuffer
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity
import net.minecraft.util.math.Box
import java.awt.Color

/**
 * @author Enaium
 */
open class Render(val boxType: BoxType, val drawType: DrawType) {
    private fun getList(name: String): VertexBuffer? {
        return CF4M.CLASS.create(RenderService::class.java).list[name]
    }

    fun drawBox(matrixStack: MatrixStack, box: Box, color: Color, module: Any) {
        val vertexBuffer = getList(CF4M.MODULE.getByInstance(module).name)
        if (vertexBuffer != null) {
            Render3DUtil.drawBox(matrixStack, vertexBuffer, box, color)
        }
    }

    enum class BoxType {
        ENTITY,
        BLOCK
    }

    enum class DrawType {
        OUTLINE,
        SOLID
    }
}