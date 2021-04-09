package com.web.blog.service;

import com.web.blog.dao.article.CompanyInfoDao;
import com.web.blog.model.article.CompanyInfo;
import com.web.blog.model.article.InfoCreateDeleteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    CompanyInfoDao companyInfoDao;


    @Override
    public boolean createCompanyInfo(InfoCreateDeleteBean infoCreateDeleteBean) {
        String uid = infoCreateDeleteBean.getUid();
        if (!uid.equals("admin"))
            return false;

        return companyInfoDao.createCompanyInfo(infoCreateDeleteBean);
    }

    @Override
    public boolean updateOfficial(CompanyInfo companyInfo) {
        String uid = companyInfo.getCurrentUid();
        if (!uid.equals("admin"))
            return false;

        return companyInfoDao.updateOfficial(companyInfo);
    }

    @Override
    public boolean updateUnofficial(CompanyInfo companyInfo) {
        return companyInfoDao.updateUnofficial(companyInfo);
    }

    @Override
    public boolean deleteCompanyInfo(InfoCreateDeleteBean infoCreateDeleteBean) {
        String uid = infoCreateDeleteBean.getUid();
        if (!uid.equals("admin"))
            return false;

        return companyInfoDao.deleteCompanyInfo(infoCreateDeleteBean);
    }

    @Override
    public CompanyInfo selectOfficial(String companyName) {
        return companyInfoDao.selectOfficial(companyName);
    }

    @Override
    public CompanyInfo selectUnofficial(String companyName) {
        return companyInfoDao.selectUnofficial(companyName);
    }

    @Override
    public boolean updateSummary(CompanyInfo companyInfo) {
        return companyInfoDao.updateSummary(companyInfo);
    }

}