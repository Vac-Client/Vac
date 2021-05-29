package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Disable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.setting.EntityListSetting
import cn.enaium.vac.client.util.TargetUtil

/**
 * @author Enaium
 */
@Module("Glow", type = RENDER)
class Glow {

    @Setting("Entities")
    private val entities = EntityListSetting(arrayListOf())

    @Event
    fun onUpdate(motioningEvent: MotioningEvent) {
        TargetUtil.getEntities().filter { entities.all.contains(it.type.lootTableId.path) }
            .forEach { it.isGlowing = true }
    }

    @Disable
    fun onDisable() {
        TargetUtil.getEntities().filter { entities.all.contains(it.type.lootTableId.path) }
            .forEach { it.isGlowing = false }
    }
}