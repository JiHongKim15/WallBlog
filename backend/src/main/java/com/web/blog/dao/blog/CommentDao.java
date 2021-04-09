package com.web.blog.dao.blog;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import com.web.blog.model.Comment;


@Mapper
@Repository
public interface CommentDao {
    boolean insert(Comment comment);
    boolean delete(int Cno);
    boolean update(Comment comment);
    List<Comment> listCommentPage(int Bno);
}
