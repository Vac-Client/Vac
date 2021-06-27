package cn.enaium.vac.client.util

import cn.enaium.vac.client.nbs.Layer
import cn.enaium.vac.client.nbs.Note
import cn.enaium.vac.client.nbs.Song
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.NoteBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.io.DataInputStream
import java.io.File
import java.io.IOException
import java.io.InputStream


object NBSReaderUtil {

    fun read(inputStream: InputStream, songFile: File): Song? {
        val layerHashMap = HashMap<Int, Layer>()
        try {
            val dataInputStream = DataInputStream(inputStream)
            var length = readShort(dataInputStream)
            var nbsversion = 0
            if (length.toInt() == 0) {
                nbsversion = dataInputStream.readByte().toInt()
                dataInputStream.readByte()
                if (nbsversion >= 3) length = readShort(dataInputStream)
            }
            val songHeight = readShort(dataInputStream)
            val title = readString(dataInputStream)
            val author = readString(dataInputStream)
            readString(dataInputStream)
            val description = readString(dataInputStream)
            val speed = readShort(dataInputStream) / 100.0f
            dataInputStream.readBoolean()
            dataInputStream.readByte()
            dataInputStream.readByte()
            readInt(dataInputStream)
            readInt(dataInputStream)
            readInt(dataInputStream)
            readInt(dataInputStream)
            readInt(dataInputStream)
            readString(dataInputStream)
            if (nbsversion >= 4) {
                dataInputStream.readByte()
                dataInputStream.readByte()
                readShort(dataInputStream)
            }
            var tick: Short = -1
            loop@ while (true) {
                val jumpTicks = readShort(dataInputStream)
                if (jumpTicks.toInt() == 0) break
                tick = (tick + jumpTicks).toShort()
                var layer: Short = -1
                while (true) {
                    val jumpLayers = readShort(dataInputStream)
                    if (jumpLayers.toInt() == 0) continue@loop
                    layer = (layer + jumpLayers).toShort()
                    val instrument = dataInputStream.readByte()
                    val key = dataInputStream.readByte()
                    if (nbsversion >= 4) {
                        dataInputStream.readByte()
                        dataInputStream.readByte()
                        readShort(dataInputStream)
                    }
                    setNote(layer.toInt(), tick.toInt(), instrument, key, layerHashMap)
                }
            }
            if (nbsversion in 1..2) length = tick
            for (i in 0 until songHeight) {
                val layer = layerHashMap[Integer.valueOf(i)]
                val name = readString(dataInputStream)
                if (nbsversion >= 4) dataInputStream.readByte()
                val volume = dataInputStream.readByte()
                if (nbsversion >= 2) dataInputStream.readByte()
                if (layer != null) {
                    layer.name = name
                    layer.volume = volume
                }
            }
            dataInputStream.readByte()
            return Song(speed, layerHashMap, songHeight, length, title, author, description, songFile!!, false)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun setNote(layerIndex: Int, ticks: Int, instrument: Byte, key: Byte, layerHashMap: HashMap<Int, Layer>) {
        var layer = layerHashMap[Integer.valueOf(layerIndex)]
        if (layer == null) {
            layer = Layer()
            layerHashMap[Integer.valueOf(layerIndex)] = layer
        }
        layer.setNote(ticks, Note(instrument, key))
    }

    fun readShort(dataInputStream: DataInputStream): Short {
        val byte1 = dataInputStream.readUnsignedByte()
        val byte2 = dataInputStream.readUnsignedByte()
        return (byte1 + (byte2 shl 8)).toShort()
    }

    fun readInt(dataInputStream: DataInputStream): Int {
        val byte1 = dataInputStream.readUnsignedByte()
        val byte2 = dataInputStream.readUnsignedByte()
        val byte3 = dataInputStream.readUnsignedByte()
        val byte4 = dataInputStream.readUnsignedByte()
        return byte1 + (byte2 shl 8) + (byte3 shl 16) + (byte4 shl 24)
    }

    fun readString(dataInputStream: DataInputStream): String {
        var length = readInt(dataInputStream)
        val builder = StringBuilder(length)
        while (length > 0) {
            var c = dataInputStream.readByte().toChar()
            if (c == '\r') c = ' '
            builder.append(c)
            length--
        }
        return builder.toString()
    }

    fun readType(blockState: BlockState): Int {
        var instru = -1
        when (blockState.get(NoteBlock.INSTRUMENT).asString().lowercase()) {
            "harp" -> instru = 0
            "bass" -> instru = 1
            "basedrum" -> instru = 2
            "snare" -> instru = 3
            "hat" -> instru = 4
            "guitar" -> instru = 5
            "flute" -> instru = 6
            "bell" -> instru = 7
            "chime" -> instru = 8
            "xylophone" -> instru = 9
            "iron_xylophone" -> instru = 10
            "cow_bell" -> instru = 11
            "didgeridoo" -> instru = 12
            "bit" -> instru = 13
            "banjo" -> instru = 14
            "pling" -> instru = 15
        }
        return instru
    }

    fun toReadable(id: Int): String {
        when (id) {
            0 -> return "Harp, Air"
            1 -> return "Bass, Wood"
            2 -> return "Basedrum, Stone"
            3 -> return "Snare, Sand"
            4 -> return "Hat, Glass"
            5 -> return "Guitar, Wool"
            6 -> return "Flute, Clay"
            7 -> return "Bell, Gold"
            8 -> return "Chime, Packed Ice"
            9 -> return "Xylophone, Bone Block"
            10 -> return "Vibraphone, Iron Block"
            11 -> return "Cow Bell, Soul Sand"
            12 -> return "Didgeridoo, Pumpkin"
            13 -> return "Bit, Emerald Block"
            14 -> return "Banjo, Hay Bale"
            15 -> return "Electric piano, GLowstone"
        }
        return "ERROR"
    }

    fun checkNoteblocks(player: BlockPos, world: World, song: Song): Boolean {
        /* Load all required Instruments and Notes for the Song */
        val keys = ArrayList<String>()
        val combos = HashSet<String>()
        val instrumentCounter = IntArray(16)
        for (layer in song.layerHashMap.values) for (i in 0 until layer.notesAtTicks.size) try {
            val msg = layer.getNote(i)!!.instrument.toString() + "_" + (layer.getNote(i)!!.key - 33)
            if (combos.contains(msg)) continue
            keys.add("" + layer.getNote(i)!!.instrument)
            combos.add(msg)
            instrumentCounter[layer.getNote(i)!!.instrument.toInt()]++
        } catch (exception: Exception) {
        }

        /* Obtain and Check all Note Blocks around Player */for (x in -5..4) for (y in -5..4) for (z in -5..4) {
            val pos = BlockPos(x + player.x, y + player.y, z + player.z)
            if (world.getBlockState(pos).block != Blocks.NOTE_BLOCK) continue  // Check for Note Blocks
            keys.remove("" + readType(world.getBlockState(pos))) // Try to remove the Instrument if it exists
            if (instrumentCounter[readType(world.getBlockState(pos))] > 0) instrumentCounter[readType(
                world.getBlockState(pos)
            )]--
        }
        if (keys.isNotEmpty()) for (i in instrumentCounter.indices) if (instrumentCounter[i] > 0) ChatUtil.error(
            "You need " + instrumentCounter[i] + " Note Blocks of Type: " + toReadable(
                i
            )
        )
        return keys.isEmpty()
    }
}