package cn.et.yitao.home.service.impl;

import cn.et.yitao.book.bean.BookSerch;
import cn.et.yitao.home.service.HomeService;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml", "file:src/main/webapp/WEB-INF/mvc-servlet.xml" })
public class HomeServiceImplTest {

    @Autowired
    HomeService homeService;

    @Test
    public void getHotBook() {
    }

    @Test
    public void getAllBookCategory() {
    }

    @Test
    public void findUserFavority() {
    }

    @Test
    public void getretrieveListBook() throws IOException, SolrServerException {
        List<BookSerch> bookSerches = homeService.getretrieveListBook("与得", 1, 10);
        for (BookSerch bookSerch:bookSerches) {
            System.out.println(bookSerch);
        }
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