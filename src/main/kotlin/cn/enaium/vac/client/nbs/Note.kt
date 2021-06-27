package cn.enaium.vac.client.nbs

import kotlin.jvm.JvmOverloads

class Note @JvmOverloads constructor(
    var instrument: Byte,
    var key: Byte,
    var velocity: Byte = 100.toByte(),
    var panning: Int = 100,
    var pitch: Short = 0.toShort()
) {

    @JvmName("setVelocity1")
    fun setVelocity(velocity: Byte) {
        this.velocity = clamp(velocity.toInt(), 0, 100).toByte()
    }

    private fun clamp(value: Int, min: Int, max: Int): Int {
        return if (value < min) {
            min
        } else {
            Math.min(value, max)
        }
    }
}