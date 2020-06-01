package com.sdu.springdata.dao;

import com.sdu.springdata.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentDao extends JpaRepository<Comment,Integer>, JpaSpecificationExecutor<Comment> {
}
