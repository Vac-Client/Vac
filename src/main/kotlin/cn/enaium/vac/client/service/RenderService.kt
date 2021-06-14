package cn.enaium.vac.client.service

import cn.enaium.cf4m.annotation.Service
import cn.enaium.cf4m.provider.ModuleProvider
import cn.enaium.cf4m.service.ModuleService
import cn.enaium.vac.client.module.render.Render
import cn.enaium.vac.client.util.Render3DUtil
import net.minecraft.client.gl.VertexBuffer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import org.lwjgl.opengl.GL11

/**
 * @author Enaium
 */
@Service
class RenderService : ModuleService {

    val list = HashMap<String, VertexBuffer>()

    override fun beforeEnable(moduleProvider: ModuleProvider) {
        if (moduleProvider.instance !is Render) return
        val vertexBuffer = VertexBuffer()
        list[moduleProvider.name] = vertexBuffer
        val render = moduleProvider.instance as Render
        val box = when (render.boxType) {
            Render.BoxType.ENTITY -> {
                Box(-0.5, 0.0, -0.5, 0.5, 1.0, 0.5)
            }
            Render.BoxType.BLOCK -> {
                Box(BlockPos.ORIGIN)
            }
        }

        when (render.drawType) {
            Render.DrawType.OUTLINE -> {
                Render3DUtil.drawOutlined(box, vertexBuffer)
            }

            Render.DrawType.SOLID -> {
                Render3DUtil.drawSolid(box, vertexBuffer)
            }
        }
    }

    override fun beforeDisable(moduleProvider: ModuleProvider) {
        val i = list[moduleProvider.name]
        if (i != null) {
            list[moduleProvider.name]!!.close()
        }
    }
}