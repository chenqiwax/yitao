package cn.et.yitao.book.dao;

import cn.et.yitao.book.bean.BookSerch;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
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
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void findBookById() {
    }

    @Test
    public void addBook() {
    }

    @Test
    public void getListBook() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void getListDimBook() {
    }

    @Test
    public void getListBookByKeywords() {
    }

    @Test
    public void bookByIdOrder() {
    }

    @Test
    public void getListAllBook() throws IOException, SolrServerException {
      /*  List<BookSerch> listAllBook = bookDao.getListAllBook();
        SolrServer solrServer = new HttpSolrServer("http://192.168.32.130:8080/solr/collection1");
        for (BookSerch bookSerch: listAllBook) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", bookSerch.getId().toString());
            document.addField("bookName", bookSerch.getBookName());
            document.addField("desc", bookSerch.getDesc());
            document.addField("bookAuthor", bookSerch.getBookAuthor());
            document.addField("price", bookSerch.getPrice());
            document.addField("imgurl", bookSerch.getImgurl());
            document.addField("catName", bookSerch.getCatName());
            solrServer.add(document);
            System.out.println(bookSerch);
        }
        solrServer.commit();*/

    }


}