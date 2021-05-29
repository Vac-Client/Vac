package cn.enaium.vac.client.screen

import cn.enaium.vac.client.mc
import it.unimi.dsi.fastutil.ints.IntOpenHashSet
import it.unimi.dsi.fastutil.ints.IntSet
import net.minecraft.text.LiteralText
import net.minecraft.text.OrderedText
import net.minecraft.util.Identifier
import org.apache.commons.io.IOUtils
import java.nio.charset.StandardCharsets

/**
 * @author Enaium
 */
object CreditScreen {
    var credit = false

    fun init(credits: ArrayList<OrderedText>, centeredLines: IntSet) {
        val resource = mc.resourceManager.getResource(Identifier("vac", "credit.txt"))
        IOUtils.readLines(resource.inputStream, StandardCharsets.UTF_8).forEach { it ->
//            mc.textRenderer.wrapLines(LiteralText(it), 274).forEach { orderedText ->
//                credits.add(orderedText)
//                if (it.startsWith("[C]")) {
//                    centeredLines.add(credits.size)
//                }
//            }

            var string = it
            val center = string.startsWith("[C]")
            val link = string.startsWith("[L]")
            if (center) {
                string = string.substring(3)
            }

            val list: List<OrderedText> = mc.textRenderer.wrapLines(LiteralText(string), 274)

            list.forEach {
                if (center || link) {
                    centeredLines.add(credits.size)
                }
                credits.add(it)
            }

            credits.add(OrderedText.EMPTY)
        }
    }
}