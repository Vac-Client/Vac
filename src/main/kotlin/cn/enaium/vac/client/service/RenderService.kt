package cn.enaium.vac.client.service

import cn.enaium.cf4m.annotation.Service
import cn.enaium.cf4m.provider.ModuleProvider
import cn.enaium.cf4m.service.ModuleService
import cn.enaium.vac.client.module.render.Render
import cn.enaium.vac.client.util.Render3DUtil
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import org.lwjgl.opengl.GL11

/**
 * @author Enaium
 */
@Service
class RenderService : ModuleService {

    val list = HashMap<String, Int>()

    override fun beforeEnable(moduleProvider: ModuleProvider) {
        if (moduleProvider.instance !is Render) return
        val gen = GL11.glGenLists(1)
        GL11.glNewList(gen, GL11.GL_COMPILE)
        list[moduleProvider.name] = gen
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
                Render3DUtil.drawOutline(box)
            }

            Render.DrawType.SOLID -> {
                Render3DUtil.drawSolid(box)
            }
        }
        GL11.glEndList()
    }

    override fun beforeDisable(moduleProvider: ModuleProvider) {
        val i = list[moduleProvider.name]
        if (i != null && i != 0) {
            GL11.glDeleteLists(i, 1)
            list[moduleProvider.name] = 0
        }
    }
}