package com.sdu.springdata.dao;

import com.sdu.springdata.domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//定义接口
public interface ArticleDao extends MongoRepository<Article,Integer> {

    /*
       自定义接口方法
     */
    //根据标题查询
    List<Article> findByNameLike(String name);

    //根据点击量查询
    List<Article> findByHitsGreaterThan(Integer hits);
}
