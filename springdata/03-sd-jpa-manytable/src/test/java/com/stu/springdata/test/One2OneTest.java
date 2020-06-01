package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.domain.Article;
import com.sdu.springdata.domain.ArticleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class One2OneTest {

    @Autowired
    private ArticleDao articleDao;

    //保存
    @Test
    public void testSave(){
        //创建文章对象
        Article article = new Article();
        article.setTitle("Spring Data Jpa");
        article.setAuthor("YTF");
        article.setCreateTime(new Date());

        //创建文章内容对象
        ArticleData articleData = new ArticleData();
        articleData.setContent("很不错的资料...");

        //建立两个对象间的关系
        article.setArticleData(articleData);
        articleData.setArticle(article);
        //保存操作
        articleDao.save(article);
    }
}
