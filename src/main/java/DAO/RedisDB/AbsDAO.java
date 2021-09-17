package DAO.RedisDB;

import redis.clients.jedis.Jedis;

public abstract class AbsDAO {
    static Jedis jedis;
    Jedis getdb(){
        jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        return jedis;
    }
}
