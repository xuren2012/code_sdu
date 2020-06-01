package com.sdu.springdata.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment,String> {

    //按aid查询到一个文章的所有评论---命名规则查询
    @Query
    List<Comment> findByAid(Integer aid);
}

