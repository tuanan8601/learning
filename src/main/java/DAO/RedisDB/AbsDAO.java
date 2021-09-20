package DAO.RedisDB;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public abstract class AbsDAO {
    Jedis getConnection() throws JedisException {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool pool = new JedisPool(jedisPoolConfig,"redis-18337.c233.eu-west-1-1.ec2.cloud.redislabs.com",
                18337,1000,"fourin1234");
        Jedis jedis = pool.getResource();

//        Jedis jedis = new Jedis("redis-18337.c233.eu-west-1-1.ec2.cloud.redislabs.com", 18337);
//        jedis.auth("fourin1234");
        System.out.println("Connected to Redis");

        return jedis;
    }
}
