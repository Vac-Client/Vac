package cn.enaium.vac.client.util

/**
 * @author Enaium
 */
class TimeUtil {
    private var lastMS = 0L
    fun convertToMS(d: Int): Int {
        return 1000 / d
    }

    private val currentMS: Long
        get() = System.nanoTime() / 1000000L

    fun hasReached(milliseconds: Long): Boolean {
        return currentMS - lastMS >= milliseconds
    }

    val delay: Long
        get() = System.currentTimeMillis() - lastMS

    fun reset() {
        lastMS = currentMS
    }

    fun setLastMS() {
        lastMS = System.currentTimeMillis()
    }

    fun setLastMS(lastMS: Long) {
        this.lastMS = lastMS
    }
}