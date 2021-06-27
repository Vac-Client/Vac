package cn.enaium.vac.client.nbs

import java.util.HashMap
import cn.enaium.vac.client.nbs.Note

class Layer {
    var notesAtTicks = HashMap<Int, Note>()
    var volume: Byte = 100
    var panning = 100
    var name = ""
    fun getNote(tick: Int): Note? {
        return notesAtTicks[tick]
    }

    fun setNote(tick: Int, note: Note) {
        notesAtTicks[tick] = note
    }
}