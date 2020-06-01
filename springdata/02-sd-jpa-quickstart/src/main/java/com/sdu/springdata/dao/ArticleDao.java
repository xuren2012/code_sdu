package com.sdu.springdata.dao;


import com.sdu.springdata.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ArticleDao extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article> {
//   //根据标题查询
//    List<Article> findByTitle(String title);
//
//    //模糊查询
//    List<Article> findByTitleLike(String title);
//
//    //根据标题、作者查询
//    List<Article> findByTitleAndAuthor(String title,String author);
//
//    //根据ID范围查询
//    List<Article> findByAidBetween(Integer startAid,Integer endAid);
//
//    List<Article> findByAidLessThan(Integer endAid);
//
//    List<Article> findByAidIn(List<Integer> aids);
//
//    //根据创建时间查询
//    List<Article> findByCreateTimeAfter(Date createTime);
//    List<Article> findByCreateTimeBefore(Date createTime);

//JPQL查询
    //位置参数绑定--按title,author查询
    /*
      占位符是从1开始
      JPQL:类似于SQL语句，需要使用实体类名代替表名，使得属性名代替字段名
           （面向对象查询）
     */
    @Query("from Article a where a.title = ?1 and a.author = ?2")
    List<Article> findByCondition(String title,String author);

    //名字参数绑定
    @Query("from Article a where a.title = :titles and a.author = :author")
    List<Article> findByCondition2(@Param("titles") String title,@Param("author") String author);

    //like模糊查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition3(@Param("title") String title);

    //排序查询
    @Query("from Article a where a.title like %:title% order by a.aid desc")
    List<Article> findByCondition4(@Param("title") String title);

    //分页查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition5(Pageable pageable, @Param("title") String title);

    //传入集合参数查询
    @Query("from Article a where a.aid in :aids")
    List<Article> findByCondition6(@Param("aids") List<Integer> aids);

    //传入Bean进行查询--SPEL（Spring 表达式）表达式查询
    @Query("from Article a where a.title = :#{#article.title} and a.author = :#{#article.author}")
    List<Article> findByCondition7(@Param("article") Article article);

    //本地SQL查询
    @Query(value = "select * from article a where a.title = ?1 and a.author = ?2",nativeQuery = true)
    List<Article> findByCondition8(String title,String author);

}
