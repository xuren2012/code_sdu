package com.sdu.springdata.dao;


import com.sdu.springdata.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleDao extends JpaRepository<Article,Integer>,
        JpaSpecificationExecutor<Article> {

}
