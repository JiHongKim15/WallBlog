package com.web.blog.service;

import com.web.blog.model.user.Blog;
import com.web.blog.model.user.Like;

public interface BoardService {
    void createBoardLike(Like like) throws Exception;
    void deleteBoardLike(Like like) throws Exception;
    int getBoardLike(Like like) throws Exception;
}
