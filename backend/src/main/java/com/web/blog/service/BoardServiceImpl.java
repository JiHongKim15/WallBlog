package com.web.blog.service;

import com.web.blog.dao.blog.BlogDao;
import com.web.blog.dao.blog.LikeDao;
import com.web.blog.model.user.Blog;
import com.web.blog.model.user.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    LikeDao likeDao;
    @Autowired
    BlogDao blogDao;
    @Override
    public void createBoardLike(Like like) throws Exception {
        likeDao.createBoardLike(like);
        likeDao.updateBoardLike(like.getBno());
    }

    @Override
    public void deleteBoardLike(Like like) throws Exception {
        likeDao.deleteBoardLike(like);
        likeDao.updateBoardLike(like.getBno());
    }

    @Override
    public int getBoardLike(Like like) throws Exception {
        return likeDao.getBoardLike(like);
    }

}
