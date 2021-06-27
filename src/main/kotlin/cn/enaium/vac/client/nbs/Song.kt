package cn.enaium.vac.client.nbs

import java.io.File
import java.util.HashMap

class Song(
    val speed: Float,
    layerHashMap: HashMap<Int, Layer>,
    songHeight: Short,
    length: Short,
    title: String,
    author: String,
    description: String,
    path: File,
    isStereo: Boolean
) : Cloneable {
    var layerHashMap = HashMap<Int, Layer>()
    var firstCustomInstrumentIndex = 0
    val songHeight: Short
    val length: Short
    val title: String
    val path: File
    val author: String
    val description: String
    val delay: Float
    val isStereo: Boolean

    init {
        delay = 20.0f / speed
        this.layerHashMap = layerHashMap
        this.songHeight = songHeight
        this.length = length
        this.title = title
        this.author = author
        this.description = description
        this.path = path
        this.isStereo = isStereo
    }
}