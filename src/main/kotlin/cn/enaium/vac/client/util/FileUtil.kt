package cn.enaium.vac.client.util

import org.apache.commons.io.FileUtils
import java.io.File
import java.io.InputStream
import java.util.*

/**
 * @author Enaium
 */
object FileUtil {
    /**
     * Read Text File
     * @param path File Path
     * @return Text Content
     */
    fun read(path: String): String {
        return FileUtils.readFileToString(File(path), "UTF-8")
    }

    /**
     * Read Jar Resource Text File
     * @param path package
     * @return Text Content
     */
    fun readResource(path: String): String {
        return this::class.java.getResourceAsStream(path)!!.bufferedReader().readText()
    }

    /**
     * Write Text File
     * @param path File Path
     * @param string Text Content
     */
    fun write(path: String, string: String) {
        FileUtils.writeStringToFile(File(path), string, "UTF-8")
    }
}