package cn.enaium.vac.client.command

import cn.enaium.cf4m.CF4M
import cn.enaium.cf4m.annotation.command.Command
import cn.enaium.cf4m.annotation.command.Exec
import cn.enaium.cf4m.annotation.command.Param
import cn.enaium.vac.client.util.ChatUtil
import cn.enaium.vac.client.util.message

/**
 * @author Enaium
 */
@Command("e", "enable")
class EnableCommand {
    @Exec
    fun exec() {
        message("Module List:")
        CF4M.MODULE.all.forEach {
            message("${it.name}:${it.enable}")
        }
    }

    @Exec
    fun exec(@Param("Module") name: String) {
        val byName = CF4M.MODULE.getByName(name)
        if (byName != null) {
            byName.enable()
            ChatUtil.success("${byName.name}:${byName.enable}")
        } else {
            ChatUtil.error("Module $name Not Found")
        }
    }
}