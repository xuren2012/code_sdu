package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class Query1Test {

    @Autowired
    private ArticleDao articleDao;

    //根据主键查询
    @Test
    public void testFindById(){
        //一个主键
        //Optional<Article> optional = articleDao.findById(8);
        //System.out.println(optional);

        //多个主键
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(9);
        list.add(10);
        List<Article> articles = articleDao.findAllById(list);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    //查询所有
    @Test
    public void testFindAll(){
        List<Article> articles = articleDao.findAll();
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    //查询所有--排序
    @Test
    public void testFindAllWithSort(){
        Sort sort = Sort.by(Sort.Order.desc("aid"));
        List<Article> articles = articleDao.findAll(sort);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    //查询所有--分页
    @Test
    public void testFindAllWithPage(){
        //处理分页条件
        //page:  当前是第几页（从0开始）  size:页大小
        Pageable pageable = PageRequest.of(0,3);
        Page<Article> page = articleDao.findAll(pageable);

        //总记录数、总页数、每页大小
        System.out.println("总记录数："+page.getTotalElements());
        System.out.println("总页数："+page.getTotalPages());
        System.out.println("页大小："+page.getSize());

        //当前页元素
        List<Article> content = page.getContent();
        for (Article article : content) {
            System.out.println(article);
        }
    }

    //查询所有--分页+排序
    @Test
    public void testFindAllWithPageAndSort(){
        //按aid倒序排列
        Sort sort = Sort.by(Sort.Order.desc("aid"));
        //当前页，页面大小，排序条件
        Pageable pageable = PageRequest.of(0,2,sort);
        Page<Article> page = articleDao.findAll(pageable);

        System.out.println("总记录数:"+page.getTotalElements());
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("页大小:"+page.getSize());

        //当前页元素
        List<Article> content = page.getContent();
        for (Article article : content) {
            System.out.println(article);
        }
    }

}
