package cn.et.yitao.home.service;

import cn.et.yitao.book.bean.BookCategory;
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
public class HomeServiceTest {

    @Autowired
    HomeService homeService;
    @Test
    public void getHotBook() {
    }

    @Test
    public void getAllBookCategory() {
        List<BookCategory> allBookCategory = homeService.getAllBookCategory();
        for (BookCategory bookCategory:allBookCategory) {
            System.out.println("=============="+bookCategory.getName());
            for (BookCategory bookCategory1:bookCategory.getCategoryList()) {
                System.out.println(bookCategory1.getName());
            }
        }
    }

    @Test
    public void findUserFavority() {
    }

    @Test
    public void getretrieveListBook() {
    }

    @Test
    public void getBookCount() {
    }

    @Test
    public void getclassifBook() {
    }

    @Test
    public void getListBookClassName() {
    }
}