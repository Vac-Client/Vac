package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.EntityRenderEvent
import cn.enaium.vac.client.module.RENDER
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.WorldRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity

/**
 * @author Enaium
 */
@Module("Hitbox", type = RENDER)
class Hitbox {
    @Event
    fun onEntityRender(entityRenderEvent: EntityRenderEvent) {
        drawBox(
            entityRenderEvent.matrices,
            entityRenderEvent.vertexConsumers.getBuffer(RenderLayer.getLines()),
            entityRenderEvent.entity,
            0.25f,
            1.0f,
            0.0f
        )
    }

    private fun drawBox(
        matrix: MatrixStack,
        vertices: VertexConsumer,
        entity: Entity,
        red: Float,
        green: Float,
        blue: Float
    ) {
        val box = entity.boundingBox.offset(-entity.x, -entity.y, -entity.z)
        WorldRenderer.drawBox(matrix, vertices, box, red, green, blue, 1.0f)
    }
}