package com.sdu.springdata.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.dao.ArticleDataDao;
import com.sdu.springdata.domain.Article;
import com.sdu.springdata.es.EsArticle;
import com.sdu.springdata.es.EsArticleDao;
import com.sdu.springdata.mongo.Comment;
import com.sdu.springdata.mongo.CommentDao;
import com.sdu.springdata.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleDataDao articleDataDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private EsArticleDao esArticleDao;

    @Autowired
    private CommentDao commentDao;

    //向mysql中保存文章及文章内容
    @Override
    public void saveArticle(Article article) {
        //向mysql中保存文章及文章内容
        /**
         * 如果存储数据先后次序不正确，会出现如下异常：
         * org.hibernate.TransientPropertyValueException:
         * object references an unsaved transient instance
         * - save the transient instance before flushing :
         * com.sdu.springdata.domain.ArticleData.article -> com.sdu.springdata.domain.Article;
         * nested exception is java.lang.IllegalStateException:
         * org.hibernate.TransientPropertyValueException:
         * object references an unsaved transient instance
         * - save the transient instance before flushing :
         * com.sdu.springdata.domain.ArticleData.article -> com.sdu.springdata.domain.Article
         */
        //错误的保存信息
        //articleDataDao.save(article.getArticleData());
        //articleDao.save(article);
        //正确的数据保存顺序
        articleDao.save(article);
        articleDataDao.save(article.getArticleData());

        //清空redis 缓存
        redisTemplate.delete("articles");

        //向es中保存数据
        EsArticle esArticle = new EsArticle();
        esArticle.setCreateTime(article.getCreateTime());
        esArticle.setContent(article.getArticleData().getContent());
        esArticle.setTitle(article.getTitle());
        esArticle.setAuthor(article.getAuthor());
        esArticle.setId(article.getAid());
        esArticleDao.save(esArticle);
    }

    //更新mysql中保存的文章及文章内容
    @Override
    public void updateArticle(Article article) {
        //更新article
        Article articleParam = new Article();
        articleParam.setTitle(article.getTitle());
        articleParam.setAid(article.getAid());
        articleParam.setAuthor(article.getAuthor());
        articleParam.setCreateTime(article.getCreateTime());
        articleDao.save(articleParam);

        //更新articleData
        articleDataDao.updateContentByAid(article.getArticleData().getContent(), article.getAid());

        //清空redis缓存
        redisTemplate.delete("articles");

        //向ES中保存数据
        EsArticle esArticle = new EsArticle();
        esArticle.setId(article.getAid());
        esArticle.setCreateTime(article.getCreateTime());
        esArticle.setContent(article.getArticleData().getContent());
        esArticle.setTitle(article.getTitle());
        esArticle.setAuthor(article.getAuthor());
        esArticleDao.save(esArticle);

    }

    //删除的文章及文章内容
    public void deleteByAid(Integer aid) {
        //1、删除articleData
        articleDataDao.deleteByAid(aid);

        //2、删除article
        articleDao.deleteById(aid);

        //3、删除mongodb中的相关评论
         /*
           a、首先根据aid查询一个comment列表
           b、删除该评论列表
          */
        List<Comment> comments = commentDao.findByAid(aid);
        commentDao.deleteAll(comments);

        //4、清空redis
        redisTemplate.delete("articles");

        //5、删除ES中相关数据
        esArticleDao.deleteById(aid);

    }

    //查询最新文章列表
    @Override
    public List<Article> findNewArticleList() {
        //1、先从redis中获取
        String value = redisTemplate.opsForValue().get("articles");

        //2、如果redis中没有，则到数据库中查询，查询成功之后，存入redis
        if (StringUtils.isEmpty(value)) {
            //分页排序条件
            Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("createTime")));
            Page<Article> page = articleDao.findAll(pageable);
            List<Article> content = page.getContent();
            //把结果转成String,存入redis
            if (content != null && content.size() > 0) {
                value = JSONObject.toJSONString(content);
                redisTemplate.opsForValue().set("articles",value);
            }
        }
        //3、把结果转成List返回
        return JSONObject.parseArray(value,Article.class);
    }

    //全文检索
    @Override
    public List<EsArticle> search(Integer pageNum, Integer pageSize, String keyword){
        //设置分页条件
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esArticleDao.findByTitleOrContent(keyword,keyword,pageable);
    }


}
