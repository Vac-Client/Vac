package cn.enaium.vac.client.module.player

import cn.enaium.cf4m.annotation.Event
import cn.enaium.cf4m.annotation.module.Enable
import cn.enaium.cf4m.annotation.module.Module
import cn.enaium.cf4m.annotation.module.Setting
import cn.enaium.vac.client.event.MotionedEvent
import cn.enaium.vac.client.event.MotioningEvent
import cn.enaium.vac.client.mc
import cn.enaium.vac.client.module.PLAYER
import cn.enaium.vac.client.nbs.Song
import cn.enaium.vac.client.setting.EnableSetting
import cn.enaium.vac.client.setting.StringSetting
import cn.enaium.vac.client.util.BlockUtil
import cn.enaium.vac.client.util.NBSReaderUtil
import cn.enaium.vac.client.util.RotationUtil
import cn.enaium.vac.client.util.TimeUtil
import net.minecraft.block.Blocks
import net.minecraft.block.NoteBlock
import net.minecraft.text.LiteralText
import net.minecraft.text.TranslatableText
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import java.io.File


/**
 * @author Enaium
 */
@Module("AutoNote", type = PLAYER)
class AutoNote {

    @Setting("File", description = "File path")
    private val path = StringSetting("./新宝岛.nbs")

    @Setting("Aim")
    private val aim = EnableSetting(false)

    private var song: Song? = null

    private val time = TimeUtil()

    private var tuning = false
    private var index = 0

    @Enable
    fun enable() {
        val file = File(path.current)
        song = NBSReaderUtil.read(file.inputStream(), file)

        getPos(5).forEach {
            if (BlockUtil.getBlock(it) == Blocks.NOTE_BLOCK) {
                println(BlockUtil.getState(it).get(NoteBlock.NOTE).toInt())
            }
        }

        tuning = true
        index = 0
    }

    private fun tune(pos: BlockPos, output: Int) {
        val currentNote: Int = BlockUtil.getState(pos).get(NoteBlock.NOTE).toInt()
        if (currentNote != output) {
            for (i in 0 until output - currentNote) {
                mc.interactionManager!!.interactBlock(
                    mc.player,
                    mc.world,
                    Hand.MAIN_HAND,
                    BlockHitResult(mc.player!!.pos, Direction.UP, pos, false)
                )
            }
        }
    }

    @Event
    fun tune(motioningEvent: MotioningEvent) {
        if (tuning) {
            val list = arrayListOf<BlockPos>()

            getPos(5).forEach {
                if (BlockUtil.getBlock(it) == Blocks.NOTE_BLOCK) {
                    list.add(it)
                }
            }

            for (i in 0 until list.size) {
                tune(list[i], i)
            }

            tuning = false
        }
    }

    @Event
    fun play(motioningEvent: MotioningEvent) {
        if (song == null) {
            return
        }

        if (time.hasReached(song!!.speed.toLong())) {
            for (layer in song!!.layerHashMap.values) {
                if (layer.getNote(index) != null) {
                    getPos(5).forEach {
                        if (BlockUtil.getBlock(it) != Blocks.NOTE_BLOCK) return@forEach
                        val readType = BlockUtil.getState(it).get(NoteBlock.NOTE).toInt()
                        if (readType == (layer.getNote(index)!!.key - 33)) {

                            if (aim.enable) {
                                motioningEvent.yaw =
                                    RotationUtil.getNeededRotations(
                                        RotationUtil.getRandomCenter(
                                            BlockUtil.getBoundingBox(
                                                it
                                            )
                                        )
                                    ).yaw
                                motioningEvent.pitch =
                                    RotationUtil.getNeededRotations(
                                        RotationUtil.getRandomCenter(
                                            BlockUtil.getBoundingBox(
                                                it
                                            )
                                        )
                                    ).pitch
                            }

                            mc.interactionManager!!.attackBlock(it, Direction.UP)
                        }
                    }
                }
            }
            index++
            time.reset()
        }
    }


    private fun getPos(range: Int): List<BlockPos> {
        val poss = arrayListOf<BlockPos>()
        val blockPos = mc.player!!.blockPos

        val maxX = blockPos.x + range
        val minX = blockPos.x - range
        val maxY = blockPos.y + range
        val minY = blockPos.y - range
        val maxZ = blockPos.z + range
        val minZ = blockPos.z - range

        for (x in minX..maxX) for (y in minY..maxY) for (z in minZ..maxZ) {
            poss.add(BlockPos(x, y, z))
        }

        return poss;
    }
}