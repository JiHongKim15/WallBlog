package com.web.blog.dao.admin;

import com.web.blog.model.article.CompanyInfo;
import com.web.blog.model.user.Blog;
import com.web.blog.model.user.UserUsedByAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("adminPage")
public interface adminPage {

    List<CompanyInfo> companyList(String companyName);

    List<UserUsedByAdmin> userList(String uid);

    List<Blog> blogList(String uid);

    boolean isUser(String uid);

    List<String> categoryList();

    boolean createCategory(String category);

    boolean deleteCategory(String category);

}