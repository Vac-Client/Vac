package cn.enaium.vac.client.util

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import org.lwjgl.glfw.GLFW

/**
 * @author Enaium
 */
object KeyboardUtil {
    fun getKey(name: String): Int {
        val all = getAll()
        if (!all.containsKey(name.uppercase())) {
            return -1
        }
        return all[name.uppercase()]!!
    }

    fun getName(key: Int): String {
        val all = getAll()
        all.forEach {
            if (it.value == key) {
                return it.key
            }
        }
        return "UNKNOWN"
    }

    private fun getAll(): Map<String, Int> {
        val map = HashMap<String, Int>()
        GLFW::class.java.fields.filter {
            it.name.startsWith("GLFW_KEY_")
        }.forEach {
            map[it.name.replace("GLFW_KEY_", "")] = it.getInt(null)
        }
        return map
    }
}