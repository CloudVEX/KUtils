package de.cloudvex.kutils.environment

import java.io.File
import java.util.*

interface Environment {
    
    fun createFile(file: File, block: (Properties) -> Unit)
    fun getProperties(file: File): Properties
    fun get(properties: Properties, key: String): String
    
}