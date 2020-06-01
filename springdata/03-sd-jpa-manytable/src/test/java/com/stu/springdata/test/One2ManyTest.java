package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.dao.CommentDao;
import com.sdu.springdata.domain.Article;
import com.sdu.springdata.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class One2ManyTest {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentDao commentDao;

    //添加
    @Test
    public void testSave(){
        //添加文章信息
        Article article = new Article();
        article.setTitle("One2Many测试");
        article.setAuthor("小崔老师");
        article.setCreateTime(new Date());

        //添加评论信息
        Comment comment1 = new Comment();
        comment1.setComment("One2Many测试,成功111...");
        Comment comment2 = new Comment();
        comment2.setComment("One2Many测试,成功222...");

        //建立关系
        comment1.setArticle(article);
//        comment2.setArticle(article);
//        HashSet<Comment> comments = new HashSet<>();
//        comments.add(comment1);
//        comments.add(comment2);
//        article.setComments(comments);
        article.getComments().add(comment2);

        //保存
        articleDao.save(article);
        commentDao.save(comment1);
        commentDao.save(comment2);
    }
}
