package com.sdu.springdata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisZSetTest {
    @Autowired
    private RedisTemplate redisTemplate;

    ZSetOperations<String,String> operations = null;

    @Before
    public void init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        operations = redisTemplate.opsForZSet();
    }

    //添加
    @Test
    public void testAdd(){
        operations.add("students","张三丰",80);
        operations.add("students","李思思",90);
        operations.add("students","王小二",70);
    }

    @Test
    public void testScore(){
        //increment功能是操作score
        //增加
        operations.incrementScore("students","张三丰",80);
        //减少
        operations.incrementScore("students","李思思",-45);
    }

    @Test
    public void testFindOne(){

        //查询一个元素的分数
        Double score = operations.score("students", "李思思");
        System.out.println(score);

        //查询一个元素在集合中的排名，从0开始
        Long rank = operations.rank("students", "李思思");
        System.out.println(rank);
    }

    //根据区间获取列表
    @Test
    public void testFindList(){

        //依据排名区间获取列表
        Set<String> students = operations.range("students", 0, 2);
        for (String student : students) {
            System.out.println(student);
        }

        Set<ZSetOperations.TypedTuple<String>> set = operations.rangeWithScores("students", 0, 2);
        for (ZSetOperations.TypedTuple<String> tuple : set) {
            System.out.println(tuple.getValue()+"同学，分值是："+tuple.getScore());
        }

        //依据分数区间获取列表
        Set<String> students2 = operations.rangeByScore("students", 60, 90);
        for (String s : students2) {
            System.out.println(s);
        }

        Set<ZSetOperations.TypedTuple<String>> set1 = operations.rangeByScoreWithScores("students", 60, 90);
        for (ZSetOperations.TypedTuple<String> tuple : set1) {
            System.out.println(tuple.getValue()+"同学，分值是："+tuple.getScore());
        }
    }

    //统计
    @Test
    public void testCount(){
        //统计一个集合中的元素
        Long zCard = operations.zCard("students");
        System.out.println(zCard);

        //依据分数区间统计元素数量
        Long count = operations.count("students", 50, 100);
        System.out.println(count);
    }

    //删除
    @Test
    public void testRemove(){
        //依据key-value删除，value可以多个
        operations.remove("students","张三","李思思");

        //依据排名区间删除
        operations.removeRange("students",0,1);

        //依据分数区间删除
        operations.removeRangeByScore("students",60,79);
    }
}
