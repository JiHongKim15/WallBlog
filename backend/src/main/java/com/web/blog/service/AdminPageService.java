package com.web.blog.service;

import com.web.blog.model.article.CompanyInfo;
import com.web.blog.model.user.Blog;
import com.web.blog.model.user.UserUsedByAdmin;

import java.util.List;

public interface AdminPageService {

    List<CompanyInfo> companyList(String companyName);

    List<UserUsedByAdmin> userList(String uid);

    List<Blog> blogList(String uid);

    boolean isUser(String uid);

    List<String> categoryList();

    boolean createCategory(String category);

    boolean deleteCategory(String category);

}