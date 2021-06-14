package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.event.Render3DEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.module.UI
import cn.enaium.vac.client.module.ui.UI
import cn.enaium.vac.client.setting.EnableSetting
import cn.enaium.vac.client.setting.IntegerSetting
import cn.enaium.vac.client.util.BlockUtil
import net.minecraft.block.Blocks
import net.minecraft.text.LiteralText
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import java.awt.Color

/**
 * @author Enaium
 */
@Module("LavaWarning", type = RENDER)
class LavaWarning : Render(BoxType.BLOCK, DrawType.OUTLINE) {

    @Setting("Range")
    private val range = IntegerSetting(3, 3, 10)

    @Setting("Render")
    private val render = EnableSetting(true)

    @Event
    fun render(render3DEvent: Render3DEvent) {
        val blockPos = mc.player!!.blockPos

        val maxX = blockPos.x + range.current
        val minX = blockPos.x - range.current
        val maxY = blockPos.y + range.current
        val minY = blockPos.y - range.current
        val maxZ = blockPos.z + range.current
        val minZ = blockPos.z - range.current

        for (x in minX..maxX) for (y in minY..maxY) for (z in minZ..maxZ) {
            val pos = BlockPos(x, y, z)
            val block = BlockUtil.getBlock(pos)
            if (block == Blocks.LAVA) {
                mc.inGameHud.setOverlayMessage(LiteralText("There is lava nearby"), false)
                if (render.enable) {
                    drawBox(render3DEvent.matrixStack, Box(pos), Color.RED, this)
                }
            }
        }
    }
}