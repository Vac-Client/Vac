package cn.enaium.vac.client.module.ui

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.provider.ModuleProvider
import cn.enaium.vac.client.util.FontUtil
import cn.enaium.vac.client.event.Render2DEvent
import cn.enaium.vac.client.module.Mod
import cn.enaium.vac.client.module.UI
import cn.enaium.vac.client.util.Render2DUtil
import net.minecraft.client.util.math.MatrixStack

/**
 * @author Enaium
 */
@Module("EnableList", type = UI)
class EnableList : UI() {
    override fun render(matrices: MatrixStack) {
        var index = y
        val sortedByDescending =
            CF4M.MODULE.all.filter { it.enable }.sortedByDescending { FontUtil.getWidth(getText(it)) }
        sortedByDescending.forEach {
            FontUtil.drawWithShadow(
                matrices,
                getText(it),
                x - FontUtil.getWidth(getText(it)) + width,
                index
            )
            index += FontUtil.height
        }

        width = FontUtil.getWidth(getText(sortedByDescending[0]))
        height = sortedByDescending.size * FontUtil.height

        defaultX = Render2DUtil.scaledWidth - FontUtil.getWidth(getText(sortedByDescending[0]))
        defaultY = 0
        super.render(matrices)
    }

    private fun getText(module: ModuleProvider): String {
        val extend = module.getExtend<Mod>()
        if (extend.tag != null) {
            return "${module.name}[${extend.tag}]"
        }
        return module.name
    }

    @Event
    fun onRender(render2DEvent: Render2DEvent) {
        render(render2DEvent.matrices)
    }
}