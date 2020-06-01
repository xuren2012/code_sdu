package com.sdu.springdata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisStringTest {

    @Autowired
    private RedisTemplate redisTemplate;

    ValueOperations<String,String> operations = null;

    @Before
    public void init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        operations = redisTemplate.opsForValue();
    }

    @Test
    public void testSet(){

        //向数据库添加数据
        operations.set("name","Tom");

        //保存数据，有效时间为10秒
        operations.set("address","复兴路", 10,TimeUnit.SECONDS);

        //替换    索引位置从0开始
        operations.set("name","ow",1);

        //当key不存在时，执行保存；否则什么都不做
        operations.setIfAbsent("name","Jerry");

        //批量保存
        Map map = new HashMap<>();
        map.put("key_map1","value_map1");
        map.put("key_map2","value_map2");
        map.put("key_map3","value_map3");

        operations.multiSet(map);

        //追加，当key存在时执行追加操作，否则执行保存操作
        operations.append("key_map4","value_map4");

    }
    @Test
    public void testGet(){
        //根据key获取value
        String value = operations.get("name");
        System.out.println(value);

        //先根据key获取value,而后根据value进行截取，[start,end]
        String value2 = operations.get("name", 2, 3);
        System.out.println(value2);

        //批量获取
        List<String> keys = new ArrayList<>();
        keys.add("name");
        keys.add("name2");
        keys.add("name3");
        List<String> values = operations.multiGet(keys);
        for (String s : values) {
            System.out.println(s);
        }

        //根据key获取value的长度
        Long size = operations.size("name");
        System.out.println(size);
    }


    @Test
    public void testIncrement(){
        //自增
        operations.set("age","18");
        operations.increment("age");
        System.out.println(operations.get("age"));
        operations.increment("age",10);
        System.out.println(operations.get("age"));

        //自减
        operations.decrement("age");
        System.out.println(operations.get("age"));

    }

    //删除
    @Test
    public void testDelete(){
        //单个删除
        redisTemplate.delete("name");

        //批量删除
        List<String> keys = new ArrayList<>();
        keys.add("name2");
        keys.add("name3");
        redisTemplate.delete(keys);
    }
}
