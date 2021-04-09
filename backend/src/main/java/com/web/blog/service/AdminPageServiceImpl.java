package com.web.blog.service;

import com.web.blog.dao.admin.adminPage;
import com.web.blog.model.article.CompanyInfo;
import com.web.blog.model.user.Blog;
import com.web.blog.model.user.UserUsedByAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminPageServiceImpl")
public class AdminPageServiceImpl implements AdminPageService {

    @Autowired
    adminPage adminPage;

    @Override
    public List<CompanyInfo> companyList(String companyName) {
        return adminPage.companyList(companyName);
    }

    @Override
    public List<UserUsedByAdmin> userList(String uid) {
        return adminPage.userList(uid);
    }

    @Override
    public List<Blog> blogList(String uid) {
        return adminPage.blogList(uid);
    }

    @Override
    public boolean isUser(String uid) {
        return adminPage.isUser(uid);
    }

    @Override
    public List<String> categoryList() {
        return adminPage.categoryList();
    }

    @Override
    public boolean createCategory(String category) {
        return adminPage.createCategory(category);
    }

    @Override
    public boolean deleteCategory(String category) {
        return adminPage.deleteCategory(category);
    }

}