/**   
* @Title elastaicTest.java 
* @Package elastatic 
* @Description: 
* @author yuwen
* @date 2017年5月10日 下午3:50:10 
* @version V1.0   
*/
package elastatic;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**  
 * @Description 
 * @author yuwen 
 * @date 2017年5月10日 下午3:50:10  
 */
public class elastaicTest {

	TransportClient transportClient;  
    //索引库名  
    String index = "megacorp";  
    //类型名称  
    String type = "employee";  
      
    @Before  
    public void before()  
    {  
    	try {
            //设置集群名称
            Settings settings = Settings.builder().put("cluster.name", "esdev-cluster")
            		//.put("client.transport.sniff", true)
            		.build();
            //创建client
            transportClient = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.37.18.201"), 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
      
    /** 
     * 通过prepareGet方法获取指定文档信息 
     */  
    @Test  
    public void testGet() {  
    	//搜索数据
        GetResponse response = transportClient.prepareGet("market-rule", "std", "1").execute().actionGet();
        //输出结果
        System.out.println(response.getSourceAsString());
        //关闭client
        transportClient.close();
    }  
      
    /** 
     * prepareUpdate更新索引库中文档，如果文档不存在则会报错 
     * @throws IOException 
     *  
     */  
    @Test  
    public void testUpdate() throws IOException  
    {  
        XContentBuilder source = XContentFactory.jsonBuilder()  
            .startObject()  
            .field("name", "will")  
            .endObject();  
          
        UpdateResponse updateResponse = transportClient  
                .prepareUpdate(index, type, "6").setDoc(source).get();  
          
        System.out.println(updateResponse.getVersion());  
    }  
  
    /** 
     * 通过prepareIndex增加文档，参数为json字符串 
     */  
    @Test  
    public void testIndexJson()  
    {  
        String source = "{\"name\":\"will\",\"age\":18}";  
        IndexResponse indexResponse = transportClient  
                .prepareIndex(index, type, "3").setSource(source).get();  
        System.out.println(indexResponse.getVersion());  
    }  
      
    /** 
     * 通过prepareIndex增加文档，参数为Map<String,Object> 
     */  
    @Test  
    public void testIndexMap()  
    {  
        Map<String, Object> source = new HashMap<String, Object>(2);  
        source.put("name", "Alice");  
        source.put("age", 16);  
        IndexResponse indexResponse = transportClient  
                .prepareIndex(index, type, "4").setSource(source).get();  
        System.out.println(indexResponse.getVersion());  
    }  
      
    /** 
     * 通过prepareIndex增加文档，参数为javaBean 
     *  
     * @throws ElasticsearchException 
     * @throws JsonProcessingException 
     */  
    @Test  
    public void testIndexBean() throws ElasticsearchException, JsonProcessingException  
    {  
//        Student stu = new Student();  
//        stu.setName("Fresh");  
//        stu.setAge(22);  
//          
//        ObjectMapper mapper = new ObjectMapper();  
//        IndexResponse indexResponse = transportClient  
//                .prepareIndex(index, type, "5").setSource(mapper.writeValueAsString(stu)).get();  
//        System.out.println(indexResponse.getVersion());  
    }  
      
    /** 
     * 通过prepareIndex增加文档，参数为XContentBuilder 
     *  
     * @throws IOException 
     * @throws InterruptedException 
     * @throws ExecutionException 
     */  
    @Test  
    public void testIndexXContentBuilder() throws IOException, InterruptedException, ExecutionException  
    {  
        XContentBuilder builder = XContentFactory.jsonBuilder()  
                .startObject()  
                .field("name", "Avivi")  
                .field("age", 30)  
                .endObject();  
        IndexResponse indexResponse = transportClient  
                .prepareIndex(index, type, "6")  
                .setSource(builder)  
                .execute().get();  
        //.execute().get();和get()效果一样  
        System.out.println(indexResponse.getVersion());  
    }  
      
//    /** 
//     * 通过prepareDelete删除文档 
//     *  
//     */  
//    @Test  
//    public void testDelete()  
//    {  
//        String id = "9";  
//        DeleteResponse deleteResponse = transportClient.prepareDelete(index,  
//                type, id).get();  
//          
//        System.out.println(deleteResponse.getVersion());  
//          
//        //删除所有记录  
//        transportClient.prepareDeleteByQuery(index).setTypes(type)  
//                .setQuery(QueryBuilders.matchAllQuery()).get();  
//    }  
//      
//    /** 
//     * 删除索引库，不可逆慎用 
//     */  
//    @Test  
//    public void testDeleteeIndex()  
//    {  
//        transportClient.admin().indices().prepareDelete("shb01","shb02").get();  
//    }  
//      
//    /** 
//     * 求索引库文档总数 
//     */  
//    @Test  
//    public void testCount()  
//    {  
//        long count = transportClient.prepareCount(index).get().getCount();  
//        System.out.println(count);  
//    }  
//      
//    /** 
//     * 通过prepareBulk执行批处理 
//     *  
//     * @throws IOException  
//     */  
//    @Test  
//    public void testBulk() throws IOException  
//    {  
//        //1:生成bulk  
//        BulkRequestBuilder bulk = transportClient.prepareBulk();  
//          
//        //2:新增  
//        IndexRequest add = new IndexRequest(index, type, "10");  
//        add.source(XContentFactory.jsonBuilder()  
//                    .startObject()  
//                    .field("name", "Henrry").field("age", 30)  
//                    .endObject());  
//          
//        //3:删除  
//        DeleteRequest del = new DeleteRequest(index, type, "1");  
//          
//        //4:修改  
//        XContentBuilder source = XContentFactory.jsonBuilder().startObject().field("name", "jack_1").field("age", 19).endObject();  
//        UpdateRequest update = new UpdateRequest(index, type, "2");  
//        update.doc(source);  
//          
//        bulk.add(del);  
//        bulk.add(add);  
//        bulk.add(update);  
//        //5:执行批处理  
//        BulkResponse bulkResponse = bulk.get();  
//        if(bulkResponse.hasFailures())  
//        {  
//            BulkItemResponse[] items = bulkResponse.getItems();  
//            for(BulkItemResponse item : items)  
//            {  
//                System.out.println(item.getFailureMessage());  
//            }  
//        }  
//        else  
//        {  
//            System.out.println("全部执行成功！");  
//        }  
//    }  
//      
//    /** 
//     * 通过prepareSearch查询索引库 
//     * setQuery(QueryBuilders.matchQuery("name", "jack")) 
//     * setSearchType(SearchType.QUERY_THEN_FETCH) 
//     *  
//     */  
//    @Test  
//    public void testSearch()  
//    {  
//        SearchResponse searchResponse = transportClient.prepareSearch(index)  
//                .setTypes(type)  
//                .setQuery(QueryBuilders.matchAllQuery()) //查询所有  
//                //.setQuery(QueryBuilders.matchQuery("name", "tom").operator(Operator.AND)) //根据tom分词查询name,默认or  
//                //.setQuery(QueryBuilders.multiMatchQuery("tom", "name", "age")) //指定查询的字段  
//                //.setQuery(QueryBuilders.queryString("name:to* AND age:[0 TO 19]")) //根据条件查询,支持通配符大于等于0小于等于19  
//                //.setQuery(QueryBuilders.termQuery("name", "tom"))//查询时不分词  
//                .setSearchType(SearchType.QUERY_THEN_FETCH)  
//                .setFrom(0).setSize(10)//分页  
//                .addSort("age", SortOrder.DESC)//排序  
//                .get();  
//          
//        SearchHits hits = searchResponse.getHits();  
//        long total = hits.getTotalHits();  
//        System.out.println(total);  
//        SearchHit[] searchHits = hits.hits();  
//        for(SearchHit s : searchHits)  
//        {  
//            System.out.println(s.getSourceAsString());  
//        }  
//    }  
//      
//    /** 
//     * 多索引，多类型查询 
//     * timeout 
//     */  
//    @Test  
//    public void testSearchsAndTimeout()  
//    {  
//        SearchResponse searchResponse = transportClient.prepareSearch("shb01","shb02").setTypes("stu","tea")  
//            .setQuery(QueryBuilders.matchAllQuery())  
//            .setSearchType(SearchType.QUERY_THEN_FETCH)  
//            .setTimeout("3")  
//            .get();  
//          
//        SearchHits hits = searchResponse.getHits();  
//        long totalHits = hits.getTotalHits();  
//        System.out.println(totalHits);  
//        SearchHit[] hits2 = hits.getHits();  
//        for(SearchHit h : hits2)  
//        {  
//            System.out.println(h.getSourceAsString());  
//        }  
//    }  
//      
//    /** 
//     * 过滤， 
//     * lt 小于 
//     * gt 大于 
//     * lte 小于等于 
//     * gte 大于等于 
//     *  
//     */  
//    @Test  
//    public void testFilter()  
//    {  
//        SearchResponse searchResponse = transportClient.prepareSearch(index)  
//                .setTypes(type)  
//                .setQuery(QueryBuilders.matchAllQuery()) //查询所有  
//                .setSearchType(SearchType.QUERY_THEN_FETCH)  
////              .setPostFilter(FilterBuilders.rangeFilter("age").from(0).to(19)  
////                      .includeLower(true).includeUpper(true))  
//                .setPostFilter(FilterBuilders.rangeFilter("age").gte(18).lte(22))  
//                .setExplain(true) //explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面  
//                .get();  
//      
//          
//        SearchHits hits = searchResponse.getHits();  
//        long total = hits.getTotalHits();  
//        System.out.println(total);  
//        SearchHit[] searchHits = hits.hits();  
//        for(SearchHit s : searchHits)  
//        {  
//            System.out.println(s.getSourceAsString());  
//        }  
//    }  
//      
//    /** 
//     * 高亮 
//     */  
//    @Test  
//    public void testHighLight()  
//    {  
//        SearchResponse searchResponse = transportClient.prepareSearch(index)  
//                .setTypes(type)  
//                //.setQuery(QueryBuilders.matchQuery("name", "Fresh")) //查询所有  
//                .setQuery(QueryBuilders.queryString("name:F*"))  
//                .setSearchType(SearchType.QUERY_THEN_FETCH)  
//                .addHighlightedField("name")  
//                .setHighlighterPreTags("<font color='red'>")  
//                .setHighlighterPostTags("</font>")  
//                .get();  
//      
//          
//        SearchHits hits = searchResponse.getHits();  
//        System.out.println("sum:" + hits.getTotalHits());  
//          
//        SearchHit[] hits2 = hits.getHits();  
//        for(SearchHit s : hits2)  
//        {  
//            Map<String, HighlightField> highlightFields = s.getHighlightFields();  
//            HighlightField highlightField = highlightFields.get("name");  
//            if(null != highlightField)  
//            {  
//                Text[] fragments = highlightField.fragments();  
//                System.out.println(fragments[0]);  
//            }  
//            System.out.println(s.getSourceAsString());  
//        }  
//    }  
//      
//    /** 
//     * 分组 
//     */  
//    @Test  
//    public void testGroupBy()  
//    {  
//        SearchResponse searchResponse = transportClient.prepareSearch(index).setTypes(type)  
//                .setQuery(QueryBuilders.matchAllQuery())  
//                .setSearchType(SearchType.QUERY_THEN_FETCH)  
//                .addAggregation(AggregationBuilders.terms("group_age")  
//                        .field("age").size(0))//根据age分组，默认返回10，size(0)也是10  
//                .get();  
//          
//        Terms terms = searchResponse.getAggregations().get("group_age");  
//        List<Bucket> buckets = terms.getBuckets();  
//        for(Bucket bt : buckets)  
//        {  
//            System.out.println(bt.getKey() + " " + bt.getDocCount());  
//        }  
//    }  
//      
//    /** 
//     * 聚合函数,本例之编写了sum，其他的聚合函数也可以实现 
//     *  
//     */  
//    @Test  
//    public void testMethod()  
//    {  
//        SearchResponse searchResponse = transportClient.prepareSearch(index).setTypes(type)  
//                .setQuery(QueryBuilders.matchAllQuery())  
//                .setSearchType(SearchType.QUERY_THEN_FETCH)  
//                .addAggregation(AggregationBuilders.terms("group_name").field("name")  
//                        .subAggregation(AggregationBuilders.sum("sum_age").field("age")))  
//                .get();  
//          
//        Terms terms = searchResponse.getAggregations().get("group_name");  
//        List<Bucket> buckets = terms.getBuckets();  
//        for(Bucket bt : buckets)  
//        {  
//            Sum sum = bt.getAggregations().get("sum_age");  
//            System.out.println(bt.getKey() + "  " + bt.getDocCount() + " "+ sum.getValue());  
//        }  
//          
//    }  
	
}
