package com.sdu.springdata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisSetTest {

    @Autowired
    private RedisTemplate redisTemplate;

    private SetOperations<String,String> operations = null;

    @Before
    public void init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        operations = redisTemplate.opsForSet();
    }

    //添加
    @Test
    public void testAdd(){
        operations.add("names","name1","name2","name3");
    }

    //查看集合中元素
    @Test
    public void testFind(){
        //查看所有元素
        Set<String> names = operations.members("names");
        for (String name : names) {
            System.out.println(name);
        }
        //随机获取一个元素
        String name = operations.randomMember("names");
        System.out.println(name);

        //随机获取多个元素，可能会重复
        List<String> list_names = operations.randomMembers("names", 2);
        for (String list_name : list_names) {
            System.out.println(list_name);
        }
    }

    //删除
    @Test
    public void testRemove(){
        //移除元素，并返回移除成功元素数量
        Long count = operations.remove("names", "name1");
        System.out.println(count);

        //随机移除指定集合中的多个元素
        List<String> names = operations.pop("names", 2);
        for (String name : names) {
            System.out.println(name);
        }

    }
    //多集合运算
    @Test
    public void testMoreSet(){
        operations.add("names1","张三","李四","王五");
        operations.add("names2","张三2","李四2","王五");

        //取交集
//        Set<String> set1 = operations.intersect("names1", "names2");
//        for (String s : set1) {
//            System.out.println(s);
//        }

        //取并集
//        Set<String> set2 = operations.union("names1", "names2");
//        for (String s : set2) {
//            System.out.println(s);
//        }

        //取差集
        //第一个集合中存在，而第二个集合不存在的元素
        //Set<String> set3 = operations.difference("names1", "names2");
        Set<String> set3 = operations.difference("names2", "names1");
        for (String s : set3) {
            System.out.println(s);
        }

    }

}
