package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class SpringDataJpaCUDTest {

    @Autowired
    private ArticleDao articleDao;

    //添加
    @Test
    public void testSave(){
        Article article = new Article();
        article.setTitle("Spring boot");
        article.setAuthor("YTF");
        article.setCreateTime(new Date());

        articleDao.save(article);
        //articleDao.saveAndFlush(article);

    }
    //添加多条数据
    @Test
    public void testSaveAll(){
        Article article = new Article();
        article.setTitle("Spring boot4");
        article.setAuthor("YTF4");
        article.setCreateTime(new Date());

        Article article2 = new Article();
        article2.setTitle("Spring boot5");
        article2.setAuthor("YTF5");
        article2.setCreateTime(new Date());

        Article article3 = new Article();
        article3.setTitle("Spring boot6");
        article3.setAuthor("YTF6");
        article3.setCreateTime(new Date());

        List list = new ArrayList();
        list.add(article);
        list.add(article2);
        list.add(article3);

        articleDao.saveAll(list);

    }

    //删除
    @Test
    public void testDeleteOne(){
        //articleDao.deleteById(8);
        //删除实体
        Article article = new Article();
        article.setAid(9);
        articleDao.delete(article);
    }

    //删除所有
    @Test
    public void testDeleteAll(){

        //删除所有：先查询，再逐条删除
        //articleDao.deleteAll();

        //删除所有：一次删除所有
        //articleDao.deleteAllInBatch();

        //批量删除指定数据
        Article article = new Article();
        article.setAid(8);
        Article article2 = new Article();
        article2.setAid(9);

        List list = new ArrayList<>();
        list.add(article);
        list.add(article2);
        //一条语句完成
        //articleDao.deleteInBatch(list);

        //先查后删除
        articleDao.deleteAll();

    }

}
