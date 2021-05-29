package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.KeyboardEvent
import cn.enaium.vac.client.event.RenderTooltipEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.setting.ItemListSetting
import cn.enaium.vac.client.util.ChatUtil.success
import cn.enaium.vac.mixin.IKeyBindingMixin
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.item.Item
import net.minecraft.text.TranslatableText
import net.minecraft.util.registry.Registry

/**
 * @author Enaium
 */
@Module("AntiDrop", type = PLAYER, description = "Anti drop item")
class AntiDrop {
    @Setting("Items")
    val itemListSetting =
        ItemListSetting(
            arrayListOf(
                "minecraft:diamond",
                "minecraft:diamond_sword",
                "minecraft:diamond_pickaxe",
                "minecraft:netherite_sword",
                "minecraft:netherite_pickaxe"
            )
        )

    var item: Item? = null

    @Event
    fun renderTooltip(renderTooltipEvent: RenderTooltipEvent) {
        if (!renderTooltipEvent.stack.isEmpty) {
            item = renderTooltipEvent.stack.item
        }
    }

    @Event
    fun onKey(keyboardEvent: KeyboardEvent) {
        if (keyboardEvent.key != (mc.options.keyDrop as IKeyBindingMixin).boundKey.code) return

        if (item != null && mc.currentScreen is InventoryScreen) {
            if (itemListSetting.all.contains(
                    Registry.ITEM.getId(
                        item
                    ).toString()
                )
            ) {
                success("AntiDrop:" + TranslatableText(item!!.translationKey).string)
                keyboardEvent.cancel = true
            }
        }

        var slot = mc.player!!.inventory.selectedSlot
        if (slot >= 36) slot -= 36
        assert(MinecraftClient.getInstance().player != null)
        val stack = MinecraftClient.getInstance().player!!.inventory.getStack(slot)
        if (!stack.isEmpty) {
            if (itemListSetting.all.contains(
                    Registry.ITEM.getId(
                        stack.item
                    ).toString()
                )
            ) {
                success("AntiDrop:" + TranslatableText(stack.translationKey).string)
                keyboardEvent.cancel = true
            }
        }
    }
}