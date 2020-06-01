package com.sdu.springdata.dao;

import com.sdu.springdata.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

//自定义接口需要继承ElasticsearchRepository<实体类型,主键类型>
public interface ArticleDao extends ElasticsearchRepository<Article,Integer> {


    /*
      需求：自定义查询接口
     */
    //根据标题查询
    List<Article> findByTitle(String title);

    //根据标题或内容查询
    List<Article> findByTitleOrContext(String title,String context);

    //根据标题或内容查询--带分页功能
    List<Article> findByTitleOrContext(String title, String context, Pageable pageable);

}
