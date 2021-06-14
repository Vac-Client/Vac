package cn.enaium.vac.client.module.render

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Disable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.event.Render3DEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.RENDER
import cn.enaium.vac.client.setting.BlockListSetting
import cn.enaium.vac.client.setting.IntegerSetting
import cn.enaium.vac.client.setting.ModeSetting
import cn.enaium.vac.client.util.BlockUtil
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.chunk.EmptyChunk
import java.awt.Color
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


/**
 * @author Enaium
 */
@Module("BlockESP", type = RENDER)
class BlockESP : Render(BoxType.BLOCK, DrawType.OUTLINE) {

    @Setting("Blocks")
    private val blockListSetting = BlockListSetting(arrayListOf("minecraft:diamond_ore", "minecraft:ancient_debris"))

    @Setting("Range")
    private val range = IntegerSetting(1, 1, 16)

    private val poss = ArrayList<BlockPos>()

    private val searchedChuck = Collections.synchronizedSet(HashSet<Chunk>())

    @Event
    fun onMotion(motioningEvent: MotioningEvent) {
        val chunks = ArrayList<Chunk>()
        val currentChunk = mc.world!!.getChunk(mc.player!!.blockPos).pos

        for (x in currentChunk.x - range.current..currentChunk.x + range.current) for (z in currentChunk.z - range.current..currentChunk.z + range.current) {
            val chunk: Chunk = mc.world!!.getChunk(x, z)
            if (chunk !is EmptyChunk) chunks.add(chunk)
        }

        chunks.forEach {
            synchronized(searchedChuck) {
                if (searchedChuck.contains(it)) return@forEach

                searchedChuck.add(it)

                val chunkPos = it.pos
                val minX = chunkPos.startX
                val minY = 0
                val minZ = chunkPos.startZ
                val maxX = chunkPos.endX
                val maxY = 255
                val maxZ = chunkPos.endZ

                for (x in minX..maxX) for (y in minY..maxY) for (z in minZ..maxZ) {
                    if (Thread.interrupted()) return
                    val pos = BlockPos(x, y, z)
                    val block = BlockUtil.getBlock(pos)
                    if (blockListSetting.all.contains(
                            Registry.BLOCK.getId(
                                block
                            ).toString()
                        )
                    ) {
                        poss.add(pos)
                    }
                }
            }
        }
    }

    @Disable
    fun disable() {
        poss.clear()
        searchedChuck.clear()
    }

    @Event
    fun onRender(render3DEvent: Render3DEvent) {
        poss.forEach {
            try {
                drawBox(render3DEvent.matrixStack, BlockUtil.getBoundingBox(it), Color.PINK, this)
            } catch (e: UnsupportedOperationException) {

            }
        }
    }
}