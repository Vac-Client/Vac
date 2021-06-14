package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.BlockBreakingProgressEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.util.BlockUtil
import net.minecraft.item.ItemStack


/**
 * @author Enaium
 */
@Module("AutoTool", description = "Auto select tool", type = PLAYER)
class AutoTool {
    @Event
    fun onBreaking(breakingProgressEvent: BlockBreakingProgressEvent) {

        if (mc.player!!.abilities.creativeMode) return

        if (mc.player!!.isBlockBreakingRestricted(
                mc.world,
                breakingProgressEvent.pos,
                mc.interactionManager!!.currentGameMode
            )
        ) return

        for (i in 0..8) {
            val stack = mc.player!!.inventory.getStack(i)

            val speed = stack.getMiningSpeedMultiplier(BlockUtil.getState(breakingProgressEvent.pos))

            if (speed > mc.player!!.mainHandStack.getMiningSpeedMultiplier(BlockUtil.getState(breakingProgressEvent.pos))) {
                mc.player!!.inventory.selectedSlot = i
            }
        }
    }
}