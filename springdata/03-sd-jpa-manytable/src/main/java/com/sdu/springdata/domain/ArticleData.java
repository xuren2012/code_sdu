package com.sdu.springdata.domain;

import javax.persistence.*;

@Entity
@Table(name="article_data")
public class ArticleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    /*
      1、此表维护实体间的关联关系
      2、建立从文章表到文章内容表的一对一关系;
      3、使用@JoinColumn声明维护关联关系，当前表中的外键articleId指向对应表中的主键aid;
         name:当前表中的外键名
         referencedColumnName:指向对应表中的主键名
     */
    @OneToOne
    @JoinColumn(name = "articleId",referencedColumnName = "aid",unique = true)
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleData{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
