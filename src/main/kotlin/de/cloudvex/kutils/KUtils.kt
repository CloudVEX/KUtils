package de.cloudvex.kutils

import de.cloudvex.kutils.breds.Breds
import de.cloudvex.kutils.breds.BredsImpl
import de.cloudvex.kutils.environment.EnvironmentImpl

class KUtils {

    fun hello(): String { return "world! :)" }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(KUtils().hello())
        }

        private val bredsImpl = BredsImpl()
        private val environmentImpl = EnvironmentImpl()

        fun breds(): Breds {
            return bredsImpl
        }

        fun env(): EnvironmentImpl {
            return environmentImpl
        }

    }

}