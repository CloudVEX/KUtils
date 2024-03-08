package de.cloudvex.kutils.breds

import redis.clients.jedis.Jedis

interface Breds {

    fun run(cache: Boolean, block: suspend (Jedis) -> Unit) {}

}