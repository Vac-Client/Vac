package cn.enaium.vac.client.setting

/**
 * @author Enaium
 */
class EnableSetting(var enable: Boolean)
class IntegerSetting(var current: Int, var min: Int, var max: Int)
class FloatSetting(var current: Float, var min: Float, var max: Float)
class DoubleSetting(var current: Double, var min: Double, var max: Double)
class LongSetting(var current: Long, var min: Long, var max: Long)
class ModeSetting(var current: String, val modes: ArrayList<String>) {
    fun getCurrentModeIndex(): Int {
        modes.withIndex().forEach { (index, ms) ->
            if (current == ms) {
                return index
            }
        }
        return -1
    }
}

class ChatListSetting(var all: ArrayList<Pair<Int, String>>)
open class ItemListSetting(var all: ArrayList<String>)
class BlockListSetting(all: ArrayList<String>) : ItemListSetting(all)
class BlockEntityListSetting(all: ArrayList<String>) : ItemListSetting(all)
class EntityListSetting(var all: ArrayList<String>)
class StringSetting(var current: String)