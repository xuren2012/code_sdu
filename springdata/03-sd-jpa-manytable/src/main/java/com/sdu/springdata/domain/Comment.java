package com.sdu.springdata.domain;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private String comment;

    /*
      建立与Article之间的多对一关系,多的一方维护关联关系
      name:当前表中的外键名，
      referencedColumnName:对应表中的主键名
     */
    @ManyToOne
    @JoinColumn(name="aid",referencedColumnName = "aid")
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", comment='" + comment + '\'' +
                '}';
    }
}
