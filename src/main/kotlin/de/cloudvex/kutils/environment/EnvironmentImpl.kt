package de.cloudvex.kutils.environment

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class EnvironmentImpl: Environment {

    override fun createFile(file: File, block: (Properties) -> Unit) {
        if (file.exists()) throw IllegalArgumentException("File already exists")

        val properties = Properties()

        block(properties)

        FileOutputStream(file).use { outputStream ->
            properties.store(outputStream, null)
        }
    }

    override fun getProperties(file: File): Properties {
        val properties = Properties()
        FileInputStream(file).use { inputStream ->
            properties.load(inputStream)
        }
        return properties
    }

    override fun get(properties: Properties, key: String): String {
        return properties.getProperty(key) ?: throw IllegalArgumentException("Key $key not found in environment")
    }

}