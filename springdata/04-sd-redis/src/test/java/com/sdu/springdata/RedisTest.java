package com.sdu.springdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        //添加数据
        redisTemplate.opsForValue().set("name1","李思");
        //获取数据
        String name = (String) redisTemplate.opsForValue().get("name1");
        System.out.println(name);
    }

    @Test
    public void test2(){
        //方式二：针对单个的模板对象实现序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //添加数据
        redisTemplate.opsForValue().set("name2","山东大学");

        //获取数据
        String name = (String) redisTemplate.opsForValue().get("name2");
        System.out.println(name);
    }
}
