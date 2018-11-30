package cn.et.yitao.home.service.impl;

import cn.et.yitao.book.bean.Book;
import cn.et.yitao.book.bean.BookCategory;
//import cn.et.yitao.book.dao.BookCategoryDao;
import cn.et.yitao.book.bean.BookSerch;
import cn.et.yitao.book.dao.BookCategoryDao;
import cn.et.yitao.book.dao.BookDao;
import cn.et.yitao.home.dao.HomeDao;
import cn.et.yitao.home.service.HomeService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:chenqi
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月10日 上午 11:00
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeDao homeDao;

    @Autowired
    BookCategoryDao bookCategoryDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    HttpSolrClient solrClient;

    /**
     * author:chenqi
     * 根据销量查询前8的书籍
     *
     * @return
     */
    public List<Book> getHotBook() {
        List<Book> hotBook = homeDao.getHotBook();
        return hotBook;
    }

    /**
     * author:chenqi
     * desc:查询所有分类
     *
     * @return
     */
    @Override
    public List<BookCategory> getAllBookCategory() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setPid(0);
        bookCategory.setIsdelete(0);
        List<BookCategory> listCategory = bookCategoryDao.getListCategory(bookCategory);
        return listCategory;
    }

    @Override
    public List<Book> findUserFavority(String uid) {
        List<Map<String, Object>> userFavoriteList = homeDao.getUserFavorite(uid);
        List<Book> listBook = new ArrayList<>();
        if (userFavoriteList != null && !userFavoriteList.isEmpty()) {
            Integer lid = Integer.parseInt(userFavoriteList.get(0).get("lid").toString());
            Book book = new Book();
            book.setLid(lid);
            listBook= bookDao.getListBook(book);
            if (listBook.size() >= 8) {
                listBook = listBook.subList(0, 8);
            }
        }
        return listBook;
    }

    @Override
    public List<BookSerch> getretrieveListBook(String keyword, int pageNum, int pagesize) throws IOException, SolrServerException {
        List<BookSerch> bookSerches = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("book_keywords:"+keyword);
       /* solrQuery.set("df", "bookName");*/
        solrQuery.setRows(pagesize);
        solrQuery.setStart(pagesize*(pageNum-1)+pagesize);
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("bookName");
        solrQuery.setHighlightSimplePre("<em style=\"color:red;\">");
        solrQuery.setHighlightSimplePost("</em>");
        solrQuery.setFacet(true);
        solrQuery.addFacetField("catName");
        QueryResponse query = solrClient.query(solrQuery);
        SolrDocumentList results = query.getResults();
        System.out.println("总数====" + results.getNumFound() + "结果数====" + results.size());
        List<FacetField> facetFields = query.getFacetFields();
        List<Map> mapList = new ArrayList<>();
        for (FacetField facetField:facetFields) {
            List<FacetField.Count> values = facetField.getValues();
            for (FacetField.Count count :values) {
                Map map = new HashMap();
                map.put("catName", count.getName());
                map.put("counts", count.getCount());
                mapList.add(map);
            }
        }

        for (SolrDocument solrDocument : results) {
            String bookName = null;
            BookSerch bookSerch = new BookSerch();
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            List<String> strings = highlighting.get(solrDocument.get("id")).get("bookName");
            if (strings != null && !strings.isEmpty()) {
                bookName = strings.get(0);
            }else {
                bookName = (String) solrDocument.get("bookName");
            }
            bookSerch.setId(Integer.valueOf((String) solrDocument.get("id")));
            bookSerch.setBookName(bookName);
            bookSerch.setBookAuthor(solrDocument.get("bookAuthor").toString());
            bookSerch.setPrice((Integer) solrDocument.get("price"));
            bookSerch.setImgurl(solrDocument.get("imgurl").toString());
            bookSerch.setCatName(solrDocument.get("catName").toString());
            bookSerches.add(bookSerch);
        }
        return bookSerches;
    }

    @Override
    public int getBookCount(String keyword) {
        return homeDao.getBookCount(keyword);
    }

    @Override
    public List<Book> getclassifBook(String classif, int pageNum, int pagesize) {
        return homeDao.getclassifBook(classif, pageNum, pagesize);
    }

    @Override
    public List<Book> getListBookClassName(String classn) {
        List<Book> listBookByClassName = homeDao.getListBookByClassName(classn);
        if (listBookByClassName != null && !listBookByClassName.isEmpty()) {
            return listBookByClassName;
        }
        return null;
    }

    @Override
    public List<Map> getAutoComplete(String keywords) throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("book_keywords:" + keywords);
        QueryResponse query = solrClient.query(solrQuery);
        SolrDocumentList results = query.getResults();
        List<Map> mapList = new ArrayList<>();
        for (SolrDocument solrDocument : results) {
            String bookAuthor = String.valueOf(solrDocument.get("bookAuthor"));
            String catName = String.valueOf(solrDocument.get("catName"));
            String bookName = String.valueOf(solrDocument.get("bookName"));
            Map map = new HashMap();
            map.put("bookAuthor", bookAuthor);
            map.put("catName", catName);
            map.put("bookName", bookName);
            mapList.add(map);
        }
        return mapList;
    }


}
