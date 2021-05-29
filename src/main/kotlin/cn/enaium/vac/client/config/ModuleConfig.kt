package cn.enaium.vac.client.config

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.annotation.config.Config
import cn.enaium.cf4m.annotation.config.Load
import cn.enaium.cf4m.annotation.config.Save
import cn.enaium.vac.client.module.ui.UI
import cn.enaium.vac.client.setting.*
import cn.enaium.vac.client.util.FileUtil
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

/**
 * @author Enaium
 */
@Config("Module")
class ModuleConfig {

    private val gson = Gson()

    @Load
    fun load() {
        val moduleObject =
            gson.fromJson(FileUtil.read(CF4M.CONFIG.getByInstance(this).path), JsonObject::class.java)
        for (module in CF4M.MODULE.all) {
            if (moduleObject != null) {
                if (moduleObject.has(module.name)) {
                    val moduleClassObject = moduleObject.getAsJsonObject(module.name)
                    if (moduleClassObject.get("enable").asBoolean) module.enable()
                    module.key = moduleClassObject.get("key").asInt

                    if (module.instance is UI) {
                        if (moduleClassObject.has("ui")) {
                            val ui = module.instance as UI
                            val uiObject = moduleClassObject.get("ui").asJsonObject
                            ui.x = uiObject.get("x").asInt
                            ui.y = uiObject.get("y").asInt
                            ui.moved = uiObject.get("moved").asBoolean
                        }
                    }

                    val settings = module.setting.all
                    if (settings.isNotEmpty()) {
                        val settingObject = moduleClassObject.getAsJsonObject("setting")
                        for (setting in settings) {
                            if (settingObject != null) {
                                if (settingObject.has(setting.name)) {
                                    when (val s = setting.getSetting<Any>()) {
                                        is EnableSetting -> {
                                            s.enable =
                                                settingObject.get(
                                                    setting.name
                                                ).asBoolean
                                        }
                                        is IntegerSetting -> {
                                            s.current =
                                                settingObject.get(setting.name).asInt
                                        }
                                        is FloatSetting -> {
                                            s.current =
                                                settingObject.get(setting.name).asFloat
                                        }
                                        is DoubleSetting -> {
                                            s.current =
                                                settingObject.get(setting.name).asDouble
                                        }
                                        is LongSetting -> {
                                            s.current =
                                                settingObject.get(setting.name).asLong
                                        }
                                        is ModeSetting -> {
                                            s.current =
                                                settingObject.get(setting.name).asString
                                        }
                                        is ItemListSetting -> {
                                            s.all.clear()
                                            settingObject.get(
                                                setting.name
                                            ).asJsonArray.forEach { s.all.add(it.asString) }
                                        }
                                        is EntityListSetting -> {
                                            s.all.clear()
                                            settingObject.get(
                                                setting.name
                                            ).asJsonArray.forEach { s.all.add(it.asString) }
                                        }
                                        is StringSetting -> {
                                            s.current = settingObject.get(setting.name).asString
                                        }
                                        is ChatListSetting -> {
                                            s.all.clear()
                                            settingObject.get(setting.name).asJsonArray.forEach {
                                                s.all.add(
                                                    gson.fromJson(
                                                        it,
                                                        object : TypeToken<Pair<Int, String>>() {

                                                        }.type
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Save
    fun save() {
        val moduleObject = JsonObject()
        for (module in CF4M.MODULE.all) {
            val moduleClassObject = JsonObject()
            moduleClassObject.addProperty("enable", module.enable)
            moduleClassObject.addProperty("key", module.key)

            if (module.instance is UI) {
                val uiObject = JsonObject()
                val ui = module.instance as UI
                uiObject.addProperty("x", ui.x)
                uiObject.addProperty("y", ui.y)
                uiObject.addProperty("moved", ui.moved)
                moduleClassObject.add("ui", uiObject)
            }

            val settings = module.setting.all
            if (settings.isNotEmpty()) {
                val settingObject = JsonObject()
                for (setting in settings) {
                    when (val s = setting.getSetting<Any>()) {
                        is EnableSetting -> {
                            settingObject.addProperty(setting.name, s.enable)
                        }
                        is IntegerSetting -> {
                            settingObject.addProperty(setting.name, s.current)
                        }
                        is FloatSetting -> {
                            settingObject.addProperty(setting.name, s.current)
                        }
                        is DoubleSetting -> {
                            settingObject.addProperty(setting.name, s.current)
                        }
                        is LongSetting -> {
                            settingObject.addProperty(setting.name, s.current)
                        }
                        is ModeSetting -> {
                            settingObject.addProperty(setting.name, s.current)
                        }
                        is ItemListSetting -> {
                            settingObject.add(
                                setting.name,
                                gson.fromJson(gson.toJson(s.all), JsonArray::class.java)
                            )
                        }
                        is EntityListSetting -> {
                            settingObject.add(
                                setting.name,
                                gson.fromJson(gson.toJson(s.all), JsonArray::class.java)
                            )
                        }
                        is StringSetting -> {
                            settingObject.addProperty(setting.name, s.current)
                        }
                        is ChatListSetting -> {
                            settingObject.add(
                                setting.name,
                                gson.fromJson(gson.toJson(s.all), JsonArray::class.java)
                            )
                        }
                    }
                }
                moduleClassObject.add("setting", settingObject)
            }
            moduleObject.add(module.name, moduleClassObject)
        }
        FileUtil.write(
            CF4M.CONFIG.getByInstance(this).path,
            gson.toJson(moduleObject)
        )
    }

}