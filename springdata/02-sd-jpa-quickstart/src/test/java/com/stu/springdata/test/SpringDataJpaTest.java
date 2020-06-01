package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class SpringDataJpaTest {

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
    }

    //修改
    @Test
    public void testUpdate(){
        Article article = new Article();
        article.setTitle("JavaWeb应用开发");
        article.setAuthor("李思");
        article.setCreateTime(new Date());
        article.setAid(1);
        articleDao.save(article);


    }

    //根据Id查询
    @Test
    public void testFindByAid(){
        Optional<Article> optional = articleDao.findById(1);
        System.out.println(optional.get());
    }

    //查询所有
    @Test
    public void testFindAll(){
        List<Article> list = articleDao.findAll();
        for (Article article : list) {
            System.out.println(article);
        }
    }

    //删除
    @Test
    public void testDelete(){
        articleDao.deleteById(2);
    }
}
