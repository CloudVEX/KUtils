package de.cloudvex.kutils.breds

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

class BredsImpl: Breds {

    companion object {

        private var host: String = "127.0.0.1"
        private var port: Int = 6379
        private var password: String = ""

    }

    // Redis Pools
    private val jedisPool: JedisPool by lazy {
        val config = JedisPoolConfig()
        if (password.isNotEmpty()) {
            JedisPool(config, host, port, 2000, password)
        } else {
            JedisPool(config, host, port, 2000)
        }
    }


    override fun setAuth(host: String, port: Int, password: String) {
        Companion.host = host
        Companion.port = port
        Companion.password = password
    }

    override fun run(cache: Boolean, block: suspend (Jedis) -> Unit) {
        runBlocking {
            val job = launch(Dispatchers.Default) {
                try {
                    jedisPool.resource.use { jedis ->
                        if (password.isNotEmpty()) {
                            jedis.auth(password)
                        }

                        block(jedis)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            job.join()
        }
    }

}