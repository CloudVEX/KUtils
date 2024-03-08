package de.cloudvex.kutils.breds

import redis.clients.jedis.Jedis

interface Breds {

    fun setAuth(host: String, port: Int, password: String) {}
    fun run(cache: Boolean, block: suspend (Jedis) -> Unit) {}

}