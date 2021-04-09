package com.web.blog.model.article;

public class InfoCreateDeleteBean {
    String companyName;
    String uid;

    public InfoCreateDeleteBean() {
    }

    public InfoCreateDeleteBean(String companyName, String uid) {
        this.companyName = companyName;
        this.uid = uid;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

}