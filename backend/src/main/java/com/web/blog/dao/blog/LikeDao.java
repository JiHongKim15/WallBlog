package com.web.blog.dao.blog;

import com.web.blog.model.user.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LikeDao {
    int getBoardLike(Like like);
    void updateBoardLike(int Bno);
    void deleteBoardLike(Like like);
    void createBoardLike(Like like);

}
