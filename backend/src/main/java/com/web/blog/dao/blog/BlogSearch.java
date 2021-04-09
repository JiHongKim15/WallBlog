package com.web.blog.dao.blog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.blog.model.user.Blog;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogSearch {

	boolean updateLike(int bno);
    Blog selectOne(int bno);
    List<Blog> selectAll(String keyword, String category);
    boolean updateView(int bno);
}
