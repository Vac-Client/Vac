package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.vac.client.event.AttackBlockEvent
import cn.enaium.vac.client.event.BlockBreakingProgressEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.setting.EnableSetting
import cn.enaium.vac.client.util.BlockUtil
import net.minecraft.block.Blocks
import net.minecraft.text.LiteralText
import net.minecraft.util.math.BlockPos

/**
 * @author Enaium
 */
@Module("AntiLava", type = PLAYER, description = "Anti break to the block next to the lava")
class AntiLava {

    @Event
    fun onAttackBlock(eventAttack: AttackBlockEvent) {
        val enableSetting = EnableSetting(eventAttack.cancel)
        `break`(eventAttack.pos, enableSetting)
        eventAttack.cancel = enableSetting.enable
    }

    @Event
    fun onBreaking(breakingProgressEvent: BlockBreakingProgressEvent) {
        val enableSetting = EnableSetting(breakingProgressEvent.cancel)
        `break`(breakingProgressEvent.pos, enableSetting)
        breakingProgressEvent.cancel = enableSetting.enable
    }

    private fun `break`(pos: BlockPos, cancel: EnableSetting) {
        val minX = pos.x - 1
        val maxX = pos.x + 1

        val minY = pos.y - 1
        val maxY = pos.y + 1

        val minZ = pos.z - 1
        val maxZ = pos.z + 1

        for (x in minX..maxX) for (y in minY..maxY) for (z in minZ..maxZ) {

            val block = BlockUtil.getBlock(BlockPos(x, y, z))
            if (block == Blocks.LAVA) {
                mc.inGameHud.setOverlayMessage(LiteralText("Anti lava"), false)
                cancel.enable = true
            }
        }
    }
}