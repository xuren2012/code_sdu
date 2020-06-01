package com.sdu.springdata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisListTest {

    @Autowired
    private RedisTemplate redisTemplate;

    private ListOperations<String,String> operations = null;

    @Before
    public void init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        operations = redisTemplate.opsForList();
    }

    //增加
    @Test
    public void testAdd(){
        //从左边添加一个元素
        operations.leftPush("students","student1");
        //从左边添加多个元素
        operations.leftPushAll("students","student2","student3","student4");

        //从右边添加一个元素
        operations.rightPush("students","student5");

        //从右边添加多个元素
        operations.rightPushAll("students","student6","student7");
    }

    //查询
    @Test
    public void testFind(){
        /*
          根据key及索引进行查询
          0及正数表示从左边开始：0 1 2
          负数表示从右边开始：-1 -2
         */
        String student = operations.index("students", 1);
        System.out.println(student);

        String student2 = operations.index("students", -1);
        System.out.println(student2);

        //范围查询--  key   [start,end] 包含首尾
        List<String> students = operations.range("students", 0, 2);
        for (String s : students) {
            System.out.println(s);
        }
    }

    //删除
    @Test
    public void testRemove(){
        //左边删除第一个元素
        String s = operations.leftPop("students");

        //右边删除第一个元素
        String s2 = operations.rightPop("students");

        /*
          count>0:删除左边起第几个等于指定值的元素
          count<0:删除右边起第几个等于指定值的元素
          count=0:删除所有等于value的元素
          删除左边起第二个sutdent3
         */
        operations.remove("students",2,"student3");

    }
}
