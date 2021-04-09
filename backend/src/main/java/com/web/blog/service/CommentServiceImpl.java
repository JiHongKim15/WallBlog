package com.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.blog.CommentDao;
import com.web.blog.model.Comment;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDao commentDao;

    @Override
    public boolean insert(Comment comment) {
        return commentDao.insert(comment);
    }

    @Override
    public boolean update(Comment comment){
        return commentDao.update(comment);
    }

    @Override
    public boolean delete(int Cno){
        return commentDao.delete(Cno);
    }

    @Override
    public List<Comment> listCommentPage(int Bno){
        return commentDao.listCommentPage(Bno);
    }
}
