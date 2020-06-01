package com.sdu.elasticsearch.test;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EsTest {

    TransportClient client = null;

    //创建连接
    @Before
    public void initClient(){
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(
                            InetAddress.getByName("192.168.0.2"), 9300
                    ));
        } catch (UnknownHostException e) {
            System.out.println("初始化失败...");
            e.printStackTrace();
        }
    }
    //关闭连接
    @After
    public void closeClient(){
        client.close();
    }

    //创建索引
    @Test
    public void testCreateIndex(){
        client.admin().indices().prepareCreate("sdu").get();
    }

    //删除索引--可以一次删除多个
    @Test
    public void testDeleteIndex(){
        client.admin().indices().prepareDelete("sdu3","sdu4","sdu2").get();
    }

    //创建映射(表结构)
    @Test
    public void testCreateMapping() throws ExecutionException, InterruptedException, IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("article")
                .startObject("properties")

                .startObject("title")
                .field("type", "string")
                .field("store", "yes")
                .field("analyzer", "ik_smart")
                .endObject()

                .startObject("content")
                .field("type", "string")
                .field("store", "yes")
                .field("analyzer", "ik_smart")
                .endObject()

                .startObject("hits")
                .field("type", "long")
                .field("store", "yes")
                .endObject()

                .endObject()
                .endObject()
                .endObject();
        //创建映射（表结构）
        PutMappingRequest mapping = Requests.putMappingRequest("sdu")//指定索引（库）
                .type("article")  //指定类型（表）
                .source(builder);
        client.admin().indices().putMapping(mapping).get();

    }

    //创建文档
    @Test
    public void testCreateDocuments(){
        //数据
        Map<String,Object> map = new HashMap<>();
        map.put("title","计算机学院");
        map.put("content","计算机学院学生...");
        map.put("hits",1000);
        //创建文档
        client.prepareIndex("sdu","article","1").setSource(map).get();
    }

    //修改文档
    @Test
    public void testUpdateDocuments(){
        //数据
        HashMap<Object, Object> map = new HashMap<>();
        map.put("title","computer");
        //修改文档
        client.prepareUpdate("sdu","article","1").setDoc(map).get();
    }

    //删除文档
    @Test
    public void testDeleteDocuments(){
        client.prepareDelete("sdu","article","1").get();
    }

    //创建文档
    @Test
    public void testCreateDocumentsForQuery(){
        for (int i = 1;i<=20;i++) {
            //数据
            Map<String, Object> map = new HashMap<>();
            map.put("title","计算机学院:" + i);
            map.put("content","计算机学院学生:" + i);
            map.put("hits",1000 + i);
        //创建文档
        client.prepareIndex("sdu","article",i+"").setSource(map).get();
        }
    }

    //查询所有
    @Test
    public void testFindAll(){
        //设置查询条件--index,type,返回一个查询结果对象
        SearchResponse searchResponse =
                client.prepareSearch("sdu").setTypes("article")//设置index,type,可以多个
                .setQuery(QueryBuilders.matchAllQuery())//设置查询条件：查询全部
                .get();
        //检索对象
        SearchHits hits = searchResponse.getHits();

        //获取查询结果数量
        System.out.println("共有"+hits.getTotalHits()+"条记录。");
        //获取结果
        Iterator<SearchHit> iterator = hits.iterator();
        while(iterator.hasNext()){
            SearchHit searchHit = iterator.next();
            System.out.println(searchHit.getSourceAsString());
        }
    }

    //根据title查询
    @Test
    public void testFindByTitle(){
        //QueryBuilders.termQuery("属性","值")
        SearchResponse searchResponse =
                client.prepareSearch("sdu")
                .setTypes("article")
                .setQuery(QueryBuilders.termQuery("title", "计算机"))
                .get();
        SearchHits hits = searchResponse.getHits();
        System.out.println("共有"+hits.getTotalHits()+"条记录。");
        Iterator<SearchHit> iterator = hits.iterator();
        while(iterator.hasNext()){
            SearchHit searchHit = iterator.next();
            System.out.println(searchHit.getSourceAsString());
        }
    }

    //分页、排序
    @Test
    public void testPageAndSort(){
        SearchResponse searchResponse = client.prepareSearch("sdu").setTypes("article")
                .setQuery(QueryBuilders.matchAllQuery())
                .setFrom(0).setSize(10)//设置分页的条件 .setFrom(从第几行开始查).setSize(查多少行)
                .addSort("hits", SortOrder.ASC)//设置分页条件 .addSort(属性, 排序规则)
                .get();
        SearchHits hits = searchResponse.getHits();
        System.out.println("共有"+hits.getTotalHits()+"条记录。");
        Iterator<SearchHit> iterator = hits.iterator();
        while(iterator.hasNext()){
            SearchHit searchHit = iterator.next();
            System.out.println(searchHit.getSourceAsString());
        }

    }


//   //创建索引
//    @Test
//    public void testCreateIndex() throws UnknownHostException {
//        //创建连接
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(
//                        new InetSocketTransportAddress(
//                                InetAddress.getByName("192.168.0.2"),9300)
//                        );
//        //创建索引
//        client.admin().indices().prepareCreate("sdu2").get();
//
//        //释放连接
//        client.close();
//    }
//
//    //删除索引
//    @Test
//    public void testDeleteIndex() throws UnknownHostException {
//        //创建连接
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new InetSocketTransportAddress(
//                        InetAddress.getByName("192.168.0.2"), 9300
//                ));
//
//        //删除索引
//        client.admin().indices().prepareDelete("sdu").get();
//        //释放连接
//        client.close();
//    }
//
//    //创建映射
//    @Test
//    public void testCreateMapping() throws IOException, ExecutionException, InterruptedException {
//        //创建连接
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(
//                        new InetSocketTransportAddress(
//                                InetAddress.getByName("192.168.0.2"), 9300
//                        ));
//
//        //准备句柄
//        XContentBuilder builder = XContentFactory.jsonBuilder()
//                .startObject()
//                .startObject("article")
//                .startObject("properties")
//
//                .startObject("title")
//                .field("type", "string")
//                .field("store", "yes")
//                .field("analyzer", "ik_smart")
//                .endObject()
//
//                .startObject("content")
//                .field("type", "string")
//                .field("store", "yes")
//                .field("analyzer", "ik_smart")
//                .endObject()
//
//                .startObject("hits")
//                .field("type", "long")
//                .field("store", "yes")
//                .endObject()
//
//                .endObject()
//                .endObject()
//                .endObject();
//
//        //创建映射--表结构
//        PutMappingRequest mapping = Requests.putMappingRequest("sdu2")
//
//                .type("article")
//                .source(builder);
//        client.admin().indices().putMapping(mapping).get();
//
//
//        //释放连接
//        client.close();
//    }
//
//    //创建文档
//    @Test
//    public void testCreateDocuments() throws UnknownHostException {
//        //创建连接
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new InetSocketTransportAddress(
//                        InetAddress.getByName("192.168.0.2"), 9300
//                ));
//        //准备数据
//        Map<String,Object> map = new HashMap<>();
//        map.put("title","山东大学");
//        map.put("content","山东大学真的很大...");
//        map.put("hits",100);
//
//        //创建文档
//        client.prepareIndex("sdu","article","1").setSource(map).get();
//        //关闭连接
//        client.close();
//
//    }
//
//    //修改文档
//    @Test
//    public void testUpdateDocuments() throws UnknownHostException {
//        //创建连接
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new InetSocketTransportAddress(
//                        InetAddress.getByName("192.168.0.2"), 9300
//                ));
//        //准备数据
//        Map<String,Object> map = new HashMap();
//        map.put("title","山东大学软件园校区");
//        map.put("content","山东大学软件园校区位于高新区...");
//        map.put("hits",200);
//        //修改文档
//        client.prepareUpdate("sdu","article","1").setDoc(map).get();
//        //关闭连接
//        client.close();
//    }
//
//    //删除文档
//    //查询所有
//    //根据title查询
//    //分页与排序

}