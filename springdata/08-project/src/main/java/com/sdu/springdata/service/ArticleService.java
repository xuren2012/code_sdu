package com.sdu.springdata.service;

import com.sdu.springdata.domain.Article;
import com.sdu.springdata.es.EsArticle;

import java.util.List;

public interface ArticleService {

    //向mysql中保存文章及文章内容
    void saveArticle(Article article);

    //更新mysql中保存的文章及文章内容
    void updateArticle(Article article);

    //删除文章及文章内容
    void deleteByAid(Integer aid);

    //最新文章列表
    List<Article> findNewArticleList();

    //文章检索
    List<EsArticle> search(Integer pageNum,Integer pageSize,String keyword);
}
