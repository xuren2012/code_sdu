package com.sdu.springdata.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;

    private String title;

    private String author;

    private Date createTime;

    /*
       建立从文章到类型的多对多关系
       声明放弃维护关系
       mappedBy= "当前类在对方类中的属性名"
     */
    @ManyToMany(mappedBy = "articles")
    private Set<Type> tpyes = new HashSet<>(0);

    public Set<Type> getTpyes() {
        return tpyes;
    }

    public void setTpyes(Set<Type> tpyes) {
        this.tpyes = tpyes;
    }

    /*
          建立与文章详情表的一对多关联关系
          在一的一方声明放弃维护关系
          mappedBy="当前类在对方类中的属性名"
         */
    @OneToMany(mappedBy = "article")
    private Set<Comment> comments = new HashSet<>(0);

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    /*
          1、声明类间关系：
             声明主动放弃关系维护,mappedBy="当前类在对方类的属性名"；
             当保存Article时，同时保存ArticleData。
          2、在类中使用注解再声明表间关系
             --书写注解；
             --明确维护关系方；
             --维护一方主动声明维护策略，不维护方声明主动放弃。
         */
    @OneToOne(mappedBy = "article",cascade = CascadeType.PERSIST)
    private ArticleData articleData;

    public ArticleData getArticleData() {
        return articleData;
    }

    public void setArticleData(ArticleData articleData) {
        this.articleData = articleData;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
