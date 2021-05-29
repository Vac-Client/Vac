package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.CF4M
import cn.enaium.vac.client.service.RenderService
import cn.enaium.vac.client.util.Render3DUtil
import net.minecraft.entity.Entity
import net.minecraft.util.math.Box
import java.awt.Color

/**
 * @author Enaium
 */
open class Render(val boxType: BoxType, val drawType: DrawType) {
    fun getList(name: String): Int? {
        return CF4M.CLASS.create(RenderService::class.java).list[name]
    }

    fun drawBox(entity: Entity, tickDelta: Float, color: Color, module: Any) {
        val list = getList(CF4M.MODULE.getByInstance(module).name)
        if (list != null) {
            Render3DUtil.drawBox(entity, tickDelta, color, list)
        }
    }

    fun drawBox(box: Box, color: Color, module: Any) {
        val list = getList(CF4M.MODULE.getByInstance(module).name)
        if (list != null) {
            Render3DUtil.drawBox(box, color, list)
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