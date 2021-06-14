package cn.enaium.vac.client

import cn.enaium.cf4m.CF4M
import cn.enaium.vac.client.util.KeyboardUtil
import cn.enaium.vac.mixin.IClientPlayerInteractionManagerMixin
import cn.enaium.vac.mixin.IMinecraftClientMixin
import cn.enaium.vac.mixin.IWorldMixin
import net.minecraft.client.MinecraftClient

/**
 * @author Enaium
 */
fun init() {
    println("Hello Vac world!")
}

fun run() {
    CF4M.run(Vac::class.java)
}

val mc = MinecraftClient.getInstance()!!
val imc = IMinecraftClient

const val VAC_NAME = "Vac"
const val VAC_VERSION = "1.0"

class Vac

object IMinecraftClient {
    val instance
        get() = MinecraftClient.getInstance() as IMinecraftClientMixin

    val world
        get() = MinecraftClient.getInstance().world as IWorldMixin

    val interactionManager
        get() = mc.interactionManager as IClientPlayerInteractionManagerMixin
}