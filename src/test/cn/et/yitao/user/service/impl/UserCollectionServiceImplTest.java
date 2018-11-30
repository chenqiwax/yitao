package cn.et.yitao.user.service.impl;

import cn.et.yitao.user.bean.UserCollection;
import cn.et.yitao.user.service.UserCollectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml", "file:src/main/webapp/WEB-INF/mvc-servlet.xml" })
public class UserCollectionServiceImplTest {

    @Autowired
    UserCollectionService userCollectionService;
    @Test
    public void getListUserCollection() throws SQLException {
        UserCollection userCollection = new UserCollection();
        List<UserCollection> listUserCollection = userCollectionService.getListUserCollection("079697649");
        for (UserCollection userCollection1:listUserCollection) {
            System.out.println("======================"+userCollection1.getBook().getName());
        }
    }

    @Test
    public void getListUserCollections() {
    }
}