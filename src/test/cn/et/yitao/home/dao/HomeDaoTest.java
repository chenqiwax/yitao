package cn.et.yitao.home.dao;

import cn.et.yitao.book.bean.Book;
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
public class HomeDaoTest {
    @Autowired
    HomeDao homeDao;

    @Test
    public void getListBookByClassName() {
        List<Book> listBookByClassName = homeDao.getListBookByClassName("励志成功");
        for (Book book : listBookByClassName) {
            System.out.println(book.getName());
        }
    }
}