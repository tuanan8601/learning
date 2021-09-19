package DAO.RedisDB;

import redis.clients.jedis.Jedis;

public abstract class AbsDAO {
    Jedis getConnection(){
        Jedis jedis = new Jedis("localhost");
//        Jedis jedis = new Jedis("redis-18337.c233.eu-west-1-1.ec2.cloud.redislabs.com", 18337);
//        jedis.auth("fourin1234");
        System.out.println("Connected to Redis");

        return jedis;
    }
}
