package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Enable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.Render3DEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.util.Render3DUtil
import cn.enaium.vac.client.util.TargetUtil
import net.minecraft.util.math.Box
import org.lwjgl.opengl.GL11
import java.awt.Color


/**
 * @author Enaium
 */
@Module("ESP", type = RENDER)
class ESP : Render(BoxType.ENTITY, DrawType.OUTLINE) {
    @Event
    fun onRender(render3DEvent: Render3DEvent) {
        TargetUtil.getEntities().forEach {
            if (it == mc.player) return@forEach
            drawBox(render3DEvent.matrixStack, it.boundingBox, Color.CYAN, this)
        }
    }
}