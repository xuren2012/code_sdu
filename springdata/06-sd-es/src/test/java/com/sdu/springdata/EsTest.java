package com.sdu.springdata;


import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-es.xml")
public class EsTest {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ArticleDao articleDao;

    //应用SpringData ES技术向Elasticsearch数据库存储一条数据
    @Test
    public void testSave(){
        //创建索引
        //template.createIndex("sdu-sd");---创建索引不成功
        template.createIndex(Article.class);

        //创建映射
        template.putMapping(Article.class);

        //创建文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("sdu_title");
        article.setContext("sdu_context");
        //保存文档
        articleDao.save(article);
    }

    //修改
    @Test
    public void testUpdate(){
        /*
          首先判断数据库中是否有指定id的文档，
           如果没有则执行保存操作，否则执行修改操作
         */
        Article article = new Article();
        article.setId(2);
        article.setHits(102);
        article.setTitle("sdu_title:2");
        article.setContext("sdu_context:2");
        articleDao.save(article);
    }

    //删除
    @Test
    public void testDelete(){
        //依据主键删除
        articleDao.deleteById(0);
    }

    //重新构建数据
    @Test
    public void makeData(){
        //创建索引
        template.createIndex(Article.class);

        //创建映射
        template.putMapping(Article.class);

        for (int i = 1; i <=10 ; i++) {
            //创建文档
            Article article = new Article();
            article.setId(i);
            article.setTitle("sdu_title:" + i);
            article.setContext("sdu_context:" + i);
            article.setHits(100 + i);

            //保存文档
            articleDao.save(article);
        }
    }

    //查询所有
    @Test
    public void testFindAll(){
        Iterable<Article> all = articleDao.findAll();
        for (Article article : all) {
            System.out.println(article);
        }
    }

    //主键查询
    @Test
    public void testFindById(){
        Optional<Article> optional = articleDao.findById(1);
        System.out.println(optional.get());
    }

    //分页查询
    @Test
    public void testFindAllWithPage(){
        //分页条件
        PageRequest pageable = PageRequest.of(1, 3);//page:页码，从0开始
        Page<Article> page = articleDao.findAll(pageable);
        for (Article article : page.getContent()) {
            System.out.println(article);
        }
    }

    //查询+排序
    @Test
    public void testFindAllWithPageAndSort(){
        //排序条件
        Sort sort = Sort.by(Sort.Order.desc("hits"));

        //分页条件
        PageRequest pageable = PageRequest.of(0, 3, sort);
        Page<Article> page = articleDao.findAll(pageable);
        for (Article article : page.getContent()) {
            System.out.println(article);
        }
    }

    /*
       自定义查询接口
     */
    //根据标题查询
    @Test
    public void testFindByTitle(){
        List<Article> articles = articleDao.findByTitle("sdu_title");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    //根据标题或内容查询
    @Test
    public void testFindByTitleOrContext(){
        List<Article> articles = articleDao.findByTitleOrContext("sdu_title", "sdu_context");
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    //根据标题或内容查询--实现分页
    @Test
    public void testFindByTitleOrContextWithPage(){
        //排序条件
        Sort sort = Sort.by(Sort.Order.desc("hits"));

        //分页条件
        PageRequest pageable = PageRequest.of(1, 3, sort);
        List<Article> articles = articleDao.findByTitleOrContext("sdu_title", "sdu", pageable);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

}
