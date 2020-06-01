package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class SpringDataJpaTest {

    @Autowired
    private ArticleDao articleDao;


    //查询所有
    @Test
    public void testFindAll(){
        List<Article> list = articleDao.findAll();
        for (Article article : list) {
            System.out.println(article);
        }
    }


}
