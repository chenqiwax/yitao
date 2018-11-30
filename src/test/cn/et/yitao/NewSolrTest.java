package cn.et.yitao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年11月09日 21:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml", "file:src/main/webapp/WEB-INF/mvc-servlet.xml" })
public class NewSolrTest {
    final String solrUrl = "http://192.168.4.110:8983/solr/items";

  /*  HttpSolrClient getSolrClient() {
        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }*/

    @Autowired
    HttpSolrClient solrClient;

    @Autowired
    HttpSolrClient.Builder builder;

    @Test
    public void testSolrQuery() throws IOException, SolrServerException {
        /*HttpSolrClient solrClient = getSolrClient();*/
        builder.withSocketTimeout(60000);
        builder.withConnectionTimeout(10000);
       solrClient = builder.build();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        QueryResponse query = solrClient.query(solrQuery);
        SolrDocumentList results = query.getResults();
        System.out.println("结果数===="+results.getNumFound());
        for (SolrDocument bookSerch: results) {
            System.out.println(bookSerch.get("bookName"));
        }
    }

    @Test
    public void testSolrQueryPlus() throws SolrServerException, IOException {
       /* HttpSolrClient solrClient = getSolrClient();*/
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.set("df", "bookName");
        solrQuery.setRows(20);
        solrQuery.setStart(0);
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
        for (FacetField facetField:facetFields) {
            List<FacetField.Count> values = facetField.getValues();
            System.out.println(values);
        }

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
    }

}
