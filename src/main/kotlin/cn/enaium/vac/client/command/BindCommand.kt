package cn.enaium.vac.client.command

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.annotation.command.Command
import cn.enaium.cf4m.annotation.command.Exec
import cn.enaium.cf4m.annotation.command.Param
import cn.enaium.cf4m.provider.ModuleProvider
import cn.enaium.vac.client.util.ChatUtil
import cn.enaium.vac.client.util.KeyboardUtil
import cn.enaium.vac.client.util.message

/**
 * @author Enaium
 */
@Command("b", "bind")
class BindCommand {
    @Exec
    fun exec() {
        message("Module List:")
        CF4M.MODULE.all.forEach {
            message("${it.name}:${KeyboardUtil.getName(it.key)}")
        }
    }

    private var byName: ModuleProvider? = null

    @Exec
    fun exec(@Param("Module") name: String) {
        byName = CF4M.MODULE.getByName(name)
        if (byName != null) {
            message("${byName!!.name}:${KeyboardUtil.getName(byName!!.key)}")
        } else {
            ChatUtil.error("Module $name Not Found")
        }
    }

    @Exec
    fun exec(@Param("Module") name: String, @Param("Key") key: String) {
        exec(name)
        if (byName != null) {
            val key1 = KeyboardUtil.getKey(key)
            if (key1 != -1) {
                byName!!.key = key1
                ChatUtil.success("${byName!!.name}:${KeyboardUtil.getName(byName!!.key)}")
            } else {
                ChatUtil.error("$key Not Found(NONE as UNKNOWN)")
            }
        }
    }
}