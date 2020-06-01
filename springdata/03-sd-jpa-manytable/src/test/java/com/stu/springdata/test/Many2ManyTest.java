package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.dao.TypeDao;
import com.sdu.springdata.domain.Article;

import com.sdu.springdata.domain.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class Many2ManyTest {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TypeDao typeDao;

    //添加
    @Test
    public void testSave(){
        //添加文章信息
        Article article1 = new Article();
        article1.setTitle("测试文章与类型关联1...");
        article1.setAuthor("崔老师");
        article1.setCreateTime(new Date());
        Article article2 = new Article();
        article2.setTitle("测试文章与类型关联2...");
        article2.setAuthor("崔老师");
        article2.setCreateTime(new Date());

        Type type1 = new Type();
        type1.setName("军事");
        Type type2 = new Type();
        type2.setName("纪实");

        //建立关系
        article1.getTpyes().add(type1);
        article1.getTpyes().add(type2);
        article2.getTpyes().add(type1);
        article2.getTpyes().add(type2);

        type1.getArticles().add(article1);
        type1.getArticles().add(article2);
        type2.getArticles().add(article1);
        type2.getArticles().add(article2);

        //保存
        articleDao.save(article1);
        articleDao.save(article2);
        typeDao.save(type1);
        typeDao.save(type2);
    }
}
