package com.sdu.springdata.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "articledata",catalog = "project")
public class ArticleData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键
    private String content;//文章内容

    /*
       建立ArticleData与Article的一对一关系
       @JoinColumn(name=当前表的外键字段名称,
                   referencedColumnName指对方表的主键)
     */
    @OneToOne
    @JoinColumn(name = "articleId", referencedColumnName = "aid", unique = true)
    private Article article;

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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "ArticleData{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    public ArticleData() {
    }
}
