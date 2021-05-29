package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.Render3DEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.util.BlockUtil
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import java.awt.Color

/**
 * @author Enaium
 */
@Module("BlockSelect", type = RENDER)
class BlockSelect : Render(BoxType.BLOCK, DrawType.OUTLINE) {
    @Event
    fun render(render3DEvent: Render3DEvent) {
        if (mc.crosshairTarget!!.type == HitResult.Type.BLOCK) {
            drawBox(
                BlockUtil.getBoundingBox((mc.crosshairTarget as BlockHitResult).blockPos),
                Color.BLUE,
                this
            )
        }
    }
}