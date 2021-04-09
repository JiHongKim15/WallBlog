package com.web.blog.dao.article;

import com.web.blog.model.article.CompanyInfo;
import com.web.blog.model.article.InfoCreateDeleteBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("CompanyInfoDao")
public interface CompanyInfoDao {

    boolean createCompanyInfo(InfoCreateDeleteBean infoCreateDeleteBean);

    boolean updateOfficial(CompanyInfo companyInfo);
    boolean updateUnofficial(CompanyInfo companyInfo);

    boolean deleteCompanyInfo(InfoCreateDeleteBean infoCreateDeleteBean);

    CompanyInfo selectOfficial(String companyName);
    CompanyInfo selectUnofficial(String companyName);

    boolean updateSummary(CompanyInfo companyInfo);

}