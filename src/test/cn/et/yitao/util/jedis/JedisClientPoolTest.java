package cn.et.yitao.util.jedis;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.util.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml", "file:src/main/webapp/WEB-INF/mvc-servlet.xml" })
public class JedisClientPoolTest {
    @Autowired
    JedisClientPool jedisClientPool;

    @Test
    public void set() {
        jedisClientPool.set("a", "123");
    }

    @Test
    public void get() {
        String a = jedisClientPool.get("a");
        System.out.println("======"+a);
    }

    @Test
    public void exists() {
    }

    @Test
    public void expire() {
    }

    @Test
    public void ttl() {
    }

    @Test
    public void incr() {
    }

    @Test
    public void hset() {
    }

    @Test
    public void hmset() {
        User user = new User();
        user.setId("123");
        user.setRegisterDate(new Date());
        user.setBirthday(new Date());
        user.setNickname("张三");
        Map map = CommonUtils.toMap(user);
        String hmset = jedisClientPool.hmset("User_"+user.getId(), map);
        System.out.println(hmset);
    }

    @Test
    public void hmget() {
    }

    @Test
    public void hget() {
    }

    @Test
    public void hdel() {
    }
}