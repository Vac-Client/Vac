package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.setting.ItemListSetting
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.util.registry.Registry


/**
 * @author Enaium
 */
@Module("AutoDrop", type = PLAYER, description = "Drop item")
class AutoDrop {

    @Setting("Items")
    val itemListSetting = ItemListSetting(arrayListOf("minecraft:rotten_flesh"))

    @Event
    fun onMotion(motioningEvent: MotioningEvent) {
        for (slot in 9..44) {
            var adjustedSlot = slot
            if (adjustedSlot >= 36) adjustedSlot -= 36
            val stack: ItemStack = mc.player!!.inventory.getStack(adjustedSlot)
            if (stack.isEmpty) continue
            val item = stack.item
            val itemName: String = Registry.ITEM.getId(item).toString()
            if (!itemListSetting.all.contains(itemName)) continue
            mc.interactionManager!!.clickSlot(0, slot, 1, SlotActionType.THROW, mc.player)
        }
    }
}