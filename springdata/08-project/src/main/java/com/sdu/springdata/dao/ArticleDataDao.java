package com.sdu.springdata.dao;

import com.sdu.springdata.domain.ArticleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleDataDao extends JpaRepository<ArticleData, Integer>,
        JpaSpecificationExecutor<ArticleData> {

    //根据aid修改ArticleData中的content
    @Transactional   //如果不添加此注解，会出现异常：Executing an update/delete query
    @Modifying
    @Query("update ArticleData ad set ad.content=?1 where ad.article.aid =?2")
    void updateContentByAid(String content, Integer adi);

    @Transactional
    @Modifying
    @Query("delete from ArticleData ad where ad.article.aid = ?1")
    void deleteByAid(Integer aid);
}
