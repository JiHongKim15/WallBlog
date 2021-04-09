package com.web.blog.dao.blog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.blog.model.user.Blog;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogDao {
	boolean insert(Blog blog);
	boolean delete(Blog blog);
	boolean update(Blog blog);

	List<Blog> mylist(String uid);
}
