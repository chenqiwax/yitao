package cn.et.yitao.util.jedis;

import java.util.List;
import java.util.Map;

public interface JedisClient {

	String set(String key, String value);
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	String hmset(String key, Map map);
	List<String> hmget(String key, String... filed);
	Long hdel(String key, String... field);
}
