package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.RenderItemEntityEvent
import cn.enaium.vac.client.module.RENDER

/**
 * @author Enaium
 */
@Module("NoItemRender", type = RENDER)
class NoItemRender {
    @Event
    fun on(renderItemEntityEvent: RenderItemEntityEvent) {
        renderItemEntityEvent.cancel = true
    }
}