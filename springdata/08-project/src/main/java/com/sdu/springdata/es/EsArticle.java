package com.sdu.springdata.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "project", type = "article")
public class EsArticle {

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store = true)
    private String title;

    @Field(type = FieldType.text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store = true)
    private String content;

    @Field(type = FieldType.text)
    private String author;

    @Field(type = FieldType.Date)
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "EsArticle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
