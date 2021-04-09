package com.web.blog.service;

import java.util.List;

import com.web.blog.model.Comment;

public interface CommentService {
    boolean insert(Comment comment);
    boolean update(Comment comment);
    boolean delete(int Cno);
    List<Comment> listCommentPage(int Bno);


}
