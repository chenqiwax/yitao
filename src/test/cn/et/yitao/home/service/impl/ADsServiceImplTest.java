package cn.et.yitao.home.service.impl;

import cn.et.yitao.home.bean.Advertising;
import cn.et.yitao.home.service.ADsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml", "file:src/main/webapp/WEB-INF/mvc-servlet.xml" })

public class ADsServiceImplTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ADsService aDsService;

    @Test
    public void selectAllADs() {
        List<Advertising> advertisingList = aDsService.selectAllADs();
        System.out.println("结果:"+advertisingList.size());
        System.out.println(advertisingList);

    }

    @Test
    public void getListDimAds() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        Boolean aBoolean = hashOperations.hasKey("map1", "id");
        System.out.println(aBoolean);
       /* Long delete = hashOperations.delete("map1", "id");
        System.out.println(delete);*/
      /*  Set map1 = hashOperations.keys("map1");
        Iterator iterator = map1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
      /*  List map1 = hashOperations.values("map1");
        System.out.println(map1);*/
        Map map1 = hashOperations.entries("map1");
        System.out.println(map1);
    }

    @Test
    public void updateAds() {

    }

    @Test
    public void addAds() {
    }

    @Test
    public void deleteAds() {
    }

    @Test
    public void getAdsById() {
    }
}