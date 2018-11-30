import cn.et.yitao.book.bean.BookSerch;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年11月06日 上午 11:13
 */
public class SolrTest {
    /*@Test
    public void testSolr() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.4.110:8080/solr/collection1");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "doc02");
        document.addField("bookName", "测试商品2");
        document.addField("price", 1234);
        solrServer.add(document);
        solrServer.commit();
    }
    @Test
    public void  testSolrDelete() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.4.110:8080/solr/collection1");
        UpdateResponse doc1 = solrServer.deleteById("change.me");
        solrServer.commit();
        System.out.println(doc1);
    }

    @Test
    public void testSolrQuery() throws SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.4.110:8080/solr/collection1");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        QueryResponse query = solrServer.query(solrQuery);
        SolrDocumentList results = query.getResults();
        System.out.println("结果数===="+results.getNumFound());
        for (SolrDocument bookSerch: results) {
                System.out.println(bookSerch.get("bookName"));
        }
    }

    @Test
    public void testSolrQueryPlus() throws SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.4.110:8080/solr/collection1");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("文学");
        solrQuery.set("df", "bookName");
        solrQuery.setRows(20);
        solrQuery.setStart(0);
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("bookName");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");
        QueryResponse query = solrServer.query(solrQuery);
        SolrDocumentList results = query.getResults();
        System.out.println("总数====" + results.getNumFound() + "结果数====" + results.size());
        for (SolrDocument solrDocument : results) {
            String bookName = null;
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            List<String> strings = highlighting.get(solrDocument.get("id")).get("bookName");
            if (strings != null && !strings.isEmpty()) {
                bookName = strings.get(0);
            }else {
                bookName = (String) solrDocument.get("bookName");
            }

            System.out.println(solrDocument.get("id"));
            System.out.println(bookName);
            System.out.println(solrDocument.get("bookAuthor"));
            System.out.println(solrDocument.get("price"));
            System.out.println(solrDocument.get("imgurl"));
            System.out.println(solrDocument.get("catName"));
            System.out.println("=====================================================");
        }
    }*/
}
