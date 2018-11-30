package cn.et.yitao.pay.service.impl;

import cn.et.yitao.pay.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml", "file:src/main/webapp/WEB-INF/mvc-servlet.xml" })
public class OrderServiceImplTest {
    @Autowired
    OrderService orderService;

    @Test
    public void getListOrderByuid() {
    }

    @Test
    public void payWXOrder() {
        orderService.payWXOrder("1540552766984581");
    }

    @Test
    public void isPayWXOrder() {
    }
}