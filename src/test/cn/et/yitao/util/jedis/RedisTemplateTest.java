package cn.et.yitao.util.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年11月05日 下午 7:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml", "file:src/main/webapp/WEB-INF/mvc-servlet.xml" })
public class RedisTemplateTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test//redis的String数据结构
    public void testRedisString(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //简单的设置值,取值
       /* valueOperations.set("name", "tom");
        Object name = valueOperations.get("name");
        System.out.println(name);*/

       //设置10秒超时自动清除,10秒后查询的结果为null
        /*valueOperations.set("namea", "toma", 10, TimeUnit.SECONDS);
        Object namea = valueOperations.get("namea");
        System.out.println(namea);*/

        //该方法是用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始,但是在这里不支持(应该是版本太低了)
       /* valueOperations.set("namea", "hello world");
        valueOperations.set("namea", "redis", 6);
        Object nameb = valueOperations.get("namea");
        System.out.println(nameb);*/
       //设置值如果存在返回false,不存在返回true
        /*Boolean aBoolean = valueOperations.setIfAbsent("namea", "namea");
        Boolean aBoolean1 = valueOperations.setIfAbsent("multia", "multia");
        System.out.println(aBoolean);
        System.out.println(aBoolean1);*/

        //为多个键分别取出他们的值
      /*  Map map = new HashMap();
        map.put("name", "张三");
        map.put("id", "123456");
        map.put("date", new Date());
        valueOperations.multiSet(map);
        List<String> keys = new ArrayList<>();
        keys.add("name");
        keys.add("id");
        keys.add("date");
        List list = valueOperations.multiGet(keys);
        System.out.println(list);
        for (Object obj:list) {
            if (obj instanceof Date) {
                Date obj1 = (Date) obj;
                System.out.println(obj1);
            }else {
                System.out.println(obj);
            }
        }*/

      //设置讲的字符串值并返回其旧值
       /* valueOperations.set("keya", "valuea");
        Object andSet = valueOperations.getAndSet("keya", "valueab");
        System.out.println(andSet);*/

        Long increlong = valueOperations.increment("increlong", 1);
        System.out.println(increlong);
        /*Object increlong1 = redisTemplate.opsForValue().get("increlong");
        System.out.println(increlong1);*/
    }

    @Test
    public void testRedisList() {
        ListOperations listOperations = redisTemplate.opsForList();
        List list = new ArrayList();
        list.add("a3");
        list.add("b3");
        list.add("c3");
        Long list1 = listOperations.leftPushAll("list2", list);
      /*  System.out.println(list1);*/
        List list11 = listOperations.range("list2", 0, -1);
        /*System.out.println(list11);*/
        /*Object o = list11.get(0);
        if (o instanceof List) {
            List o1 = (List) o;
            System.out.println(o1.size());
        }*/
        Set keys = redisTemplate.keys("*");
        for (Object obj:keys) {
            System.out.println(obj);
        }
    }
}
