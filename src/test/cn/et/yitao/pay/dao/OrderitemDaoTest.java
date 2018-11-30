package cn.et.yitao.pay.dao;

import cn.et.yitao.pay.bean.Orderitem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml", "file:src/main/webapp/WEB-INF/mvc-servlet.xml" })
public class OrderitemDaoTest {
    @Autowired
    OrderitemDao orderitemDao;

    @Test
    public void getListOrderitemByoid() {
        List<Orderitem> listOrderitemByoid = orderitemDao.getListOrderitemByoid(105);
        for (Orderitem orderitem:listOrderitemByoid) {
            System.out.println("===========" + orderitem);
        }
    }

    @Test
    public void orderitemAdd() {
    }
}