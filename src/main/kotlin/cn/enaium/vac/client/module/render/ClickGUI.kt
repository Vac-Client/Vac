package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.annotation.module.Enable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.screen.click.ClickGUI as cg
import org.lwjgl.glfw.GLFW

/**
 * @author Enaium
 */
@Module("ClickGUI", key = GLFW.GLFW_KEY_RIGHT_SHIFT, type = RENDER)
class ClickGUI {
    @Enable
    fun onEnable() {
        mc.openScreen(cg())
        CF4M.MODULE.getByInstance(this).enable()
    }
}