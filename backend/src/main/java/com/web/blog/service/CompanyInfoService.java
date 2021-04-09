package com.web.blog.service;

import com.web.blog.model.article.CompanyInfo;
import com.web.blog.model.article.InfoCreateDeleteBean;

public interface CompanyInfoService {

    boolean createCompanyInfo(InfoCreateDeleteBean infoCreateDeleteBean);

    boolean updateOfficial(CompanyInfo companyInfo);
    boolean updateUnofficial(CompanyInfo companyInfo);

    boolean deleteCompanyInfo(InfoCreateDeleteBean infoCreateDeleteBean);

    CompanyInfo selectOfficial(String companyName);
    CompanyInfo selectUnofficial(String companyName);

    boolean updateSummary(CompanyInfo companyInfo);

}