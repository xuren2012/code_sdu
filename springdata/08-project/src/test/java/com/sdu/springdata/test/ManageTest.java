package com.sdu.springdata.test;

import com.sdu.springdata.domain.Article;
import com.sdu.springdata.domain.ArticleData;
import com.sdu.springdata.mongo.Comment;
import com.sdu.springdata.service.ArticleService;
import com.sdu.springdata.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ManageTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    //向mysql中保存文章及文章内容
    @Test
    public void testSaveArticle() {
        //准备数据
        ArticleData articleData = new ArticleData();
        articleData.setContent("这是一篇文章的内容，此处省略若干字...-4月21日");

        Article article = new Article();
        article.setTitle("文章标题-4月21日");
        article.setAuthor("文章作者-4月21日");
        article.setCreateTime(new Date());

        //建立两者关系
        article.setArticleData(articleData);
        articleData.setArticle(article);

        articleService.saveArticle(article);
    }

    //更新mysql中保存的文章及文章内容
    @Test
    public void testUpdateArticle() {
        //准备数据
        ArticleData articleData = new ArticleData();
        articleData.setContent("这是测试更新功能的测试数据...");
        Article article = new Article();
        article.setAid(2);
        article.setTitle("测试更新文章标题数据");
        article.setAuthor("测试更新文章作者数据");
        article.setCreateTime(new Date());
        article.setArticleData(articleData);

        articleService.updateArticle(article);
    }

    //删除文章及文章内容
    @Test
    public void testDeleteArticle() {
        articleService.deleteByAid(2);
    }

    //添加评论
    @Test
    public void testSaveComment() {
        Comment comment = new Comment();
        comment.setCid(UUID.randomUUID().toString());
        comment.setAid(12);
        comment.setComment("这是第n条评论信息...");
        comment.setNickname("第n条评论者昵称...");
        commentService.saveComment(comment);

    }

    //删除评论
    @Test
    public void testDeleteComment() {
        commentService.deleteByCid("92989f78-bdc9-4465-9a22-61a0aed4c759");
    }


}
