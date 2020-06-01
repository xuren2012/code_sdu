package com.sdu.springdata.service.impl;

import com.sdu.springdata.mongo.Comment;
import com.sdu.springdata.mongo.CommentDao;
import com.sdu.springdata.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void deleteByCid(String cid) {
        commentDao.deleteById(cid);
    }

    @Override
    public List<Comment> findCommentsByAid(Integer aid) {
        return commentDao.findByAid(aid);
    }
}
