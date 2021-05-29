package cn.enaium.vac.client.module.ui

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.VAC_NAME
import cn.enaium.vac.client.VAC_VERSION
import cn.enaium.vac.client.event.Render2DEvent
import cn.enaium.vac.client.module.UI
import net.minecraft.client.util.math.MatrixStack
import org.lwjgl.glfw.GLFW

/**
 * @author Enaium
 */
@Module("Logo", key = GLFW.GLFW_KEY_O, type = UI)
class Logo : UI() {
    override fun render(matrices: MatrixStack) {
        FontUtil.draw(matrices, "$VAC_NAME-$VAC_VERSION", x, y)
        width = FontUtil.getWidth("$VAC_NAME-$VAC_VERSION")
        height = FontUtil.height
        super.render(matrices)
    }


    @Event
    fun onRender(render2DEvent: Render2DEvent) {
        render(render2DEvent.matrices)
    }
}