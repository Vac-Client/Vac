package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.Render3DEvent
import cn.enaium.vac.client.imc
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.setting.BlockEntityListSetting
import cn.enaium.vac.client.util.BlockUtil
import net.minecraft.block.entity.*
import net.minecraft.util.math.BlockPos
import java.awt.Color

/**
 * @author Enaium
 */
@Module("BlockEntityESP", type = RENDER)
class BlockEntityESP : Render(BoxType.BLOCK, DrawType.SOLID) {

    @Setting("BlockEntities")
    private val blockEntities = BlockEntityListSetting(
        arrayListOf(
            "minecraft:furnace",
            "minecraft:chest",
            "minecraft:trapped_chest",
            "minecraft:ender_chest",
            "minecraft:jukebox",
            "minecraft:dispenser",
            "minecraft:dropper",
            "minecraft:brewing_stand",
            "minecraft:barrel",
            "minecraft:smoker",
            "minecraft:blast_furnace"
        )
    )

    @Event
    fun onRender(render3DEvent: Render3DEvent) {
        getTargets().forEach {
            try {
                drawBox(render3DEvent.matrixStack, BlockUtil.getBoundingBox(it), Color.GREEN, this)
            } catch (e: UnsupportedOperationException) {

            }
        }
    }

    private fun getTargets(): ArrayList<BlockPos> {
        val blocks: ArrayList<BlockPos> = ArrayList()
        for (blockEntity in imc.world.blockEntityTickers) {
            val lootTableId = BlockUtil.getBlock(blockEntity.pos).lootTableId
            var path = lootTableId.path
            if (path.contains("/")) {
                path = path.substring(
                    path.indexOf(
                        "/"
                    ) + 1
                )
            }

            if (blockEntities.all.contains(
                    lootTableId.namespace + ":" + path
                )
            ) {
                blocks.add(blockEntity.pos)
            }
        }
        return blocks
    }
}