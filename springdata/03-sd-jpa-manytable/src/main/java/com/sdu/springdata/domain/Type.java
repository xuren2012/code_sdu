package com.sdu.springdata.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    private String name;

    /*
      建立从类型表到文章表的多对多的关系
     */
    @ManyToMany
    @JoinTable(
            //中间表名称
            name="article_type",
            //中间表的外键字段关联当前实体类所对应的主键字段
            joinColumns = {@JoinColumn(name="tid",referencedColumnName = "tid")},
            //中间表的外键字段关联对方类所对应表的主键字段
            inverseJoinColumns = {@JoinColumn(name="aid",referencedColumnName = "aid")}
    )
    private Set<Article> articles = new HashSet<>(0);

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                '}';
    }
}
