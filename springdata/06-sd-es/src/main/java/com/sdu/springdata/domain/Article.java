package com.sdu.springdata.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//indexName:索引名称，type:类型
@Document(indexName = "sdu-sd",type = "article")
public class Article {

    @Id
    @Field(index = false,type= FieldType.Integer)
    private Integer id;

    /**
     * index:是否设置分词，默认为true
     * analyzer:存储时使用的分词吅
     * searchAnalyzer:搜索时使用的分词器
     * store:是否存储，默认是false
     * type:数据类型，默认为FiledType.Auto
     */
    @Field(analyzer = "ik_smart",searchAnalyzer = "ik_smart",store = true,type=FieldType.text)
    private String title;

    @Field(analyzer = "ik_smart",searchAnalyzer = "ik_smart",store = true,type = FieldType.text)
    private String context;

    @Field(store = true,type=FieldType.Integer)
    private Integer hits;

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", hits=" + hits +
                '}';
    }
}
