package cn.enaium.vac.client.util

import cn.enaium.cf4m.CF4M
import cn.enaium.vac.client.VAC_NAME
import cn.enaium.vac.client.mc
import net.minecraft.text.LiteralText
import net.minecraft.util.Formatting

/**
 * @author Enaium
 */

fun message(message: String) {
    mc.inGameHud.chatHud.addMessage(LiteralText("[" + Formatting.GRAY + VAC_NAME + Formatting.WHITE + "]" + message))
}

object ChatUtil {
    private val success: String = "[" + Formatting.GREEN + "success" + Formatting.WHITE + "] "
    private val warning: String = "[" + Formatting.YELLOW + "warning" + Formatting.WHITE + "] "
    private val error: String = "[" + Formatting.RED + "error" + Formatting.WHITE + "] "

    fun error(string: String) {
        CF4M.CONFIGURATION.command.message(error + string)
    }

    fun warning(string: String) {
        CF4M.CONFIGURATION.command.message(warning + string)
    }

    fun success(string: String) {
        CF4M.CONFIGURATION.command.message(success + string)
    }
}