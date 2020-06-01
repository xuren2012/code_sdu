package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class Query3Test {

    @Autowired
    private ArticleDao articleDao;

    //JPQL
    @Test
    public void testFindByCondition(){
        List<Article> articles = articleDao.findByCondition("Spring boot", "YTF");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition2(){
        List<Article> articles = articleDao.findByCondition2("Spring boot", "YTF");
        for (Article article : articles) {
            System.out.println(article);
        }
    }
    @Test
    public void testFindByCondition3(){
        List<Article> articles = articleDao.findByCondition3("Spring boot");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition4(){
        List<Article> articles = articleDao.findByCondition4("Spring boot");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition5(){
        Pageable pageable = PageRequest.of(0,3);
        List<Article> articles = articleDao.findByCondition5(pageable,"Spring boot");
        for (Article article : articles) {
            System.out.println(article);
        }
    }
    @Test
    public void testFindByCondition6(){
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(9);
        List<Article> articles = articleDao.findByCondition6(list);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition7(){
        Article articleParam = new Article();
        articleParam.setTitle("Spring boot");
        articleParam.setAuthor("YTF");
        List<Article> articles = articleDao.findByCondition7(articleParam);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindByCondition8(){
        List<Article> articles = articleDao.findByCondition8("Spring boot", "YTF");
        for (Article article : articles) {
            System.out.println(article);
        }
    }





}
