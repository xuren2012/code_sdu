package com.sdu.springdata;

import com.sdu.springdata.domain.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisHashTest {

    @Autowired
    private RedisTemplate redisTemplate;

    HashOperations<String,String, Article> operations = null;

    @Before
    public void init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        operations = redisTemplate.opsForHash();
    }
    //保存
    @Test
    public void testPut(){
//        Article article = new Article();
//        article.setTitle("Hash-title2");
//        article.setAuthor("Hash-author2");
//        article.setCreateTime(new Date());
//        operations.put("article","2",article);

        Map<String,Article> map = new HashMap<>();
        Article article = new Article();
        article.setTitle("Hash-title2");
        article.setAuthor("Hash-author2");
        article.setCreateTime(new Date());
        map.put("3",article);

        Article article2 = new Article();
        article.setTitle("Hash-title2");
        article.setAuthor("Hash-author2");
        article.setCreateTime(new Date());
        map.put("4",article2);
        operations.putAll("article",map);
    }

    //获取
    @Test
    public void testGet(){
        //判断hashkey是否存在
        Boolean flag = operations.hasKey("article", "1");
        System.out.println(flag);

        //根据key,hashkey获取数据
        Article article = operations.get("article", "1");
        System.out.println(article);

        //根据key获取所有的hashkey
        Set<String> set = operations.keys("article");
        for (String s : set) {
            System.out.println(s);
        }

        //获取所有值
        List<Article> articles = operations.values("article");
        for (Article art : articles) {
            System.out.println(art);
        }

        Map<String, Article> map = operations.entries("article");
        for(Map.Entry<String,Article> entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    //删除
    @Test
    public void testDelete(){
        //当hash中的数据全部被删除时，整个hash就不存在啦
        operations.delete("article","3","4");
    }

}
