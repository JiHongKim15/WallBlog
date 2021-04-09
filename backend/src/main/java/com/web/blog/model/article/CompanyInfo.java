package com.web.blog.model.article;

public class CompanyInfo {

    private int infoNo;
    private String companyName;
    private String contents;
    private boolean isOfficial;
    private String createdDate;
    private String lastModifiedUid;
    private String lastModifiedDate;

    private String currentUid;
    private String summary;

    public CompanyInfo() {
    }

    public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public CompanyInfo(int infoNo, String companyName, String contents, boolean isOfficial, String createdDate, String lastModifiedUid, String lastModifiedDate, String currentUid) {
        this.infoNo = infoNo;
        this.companyName = companyName;
        this.contents = contents;
        this.isOfficial = isOfficial;
        this.createdDate = createdDate;
        this.lastModifiedUid = lastModifiedUid;
        this.lastModifiedDate = lastModifiedDate;
        this.currentUid = currentUid;
    }

    public int getInfoNo() {
        return infoNo;
    }
    public void setInfoNo(int infoNo) {
        this.infoNo = infoNo;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isOfficial() {
        return isOfficial;
    }
    public void setOfficial(boolean official) {
        isOfficial = official;
    }

    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedUid() {
        return lastModifiedUid;
    }
    public void setLastModifiedUid(String lastModifiedUid) {
        this.lastModifiedUid = lastModifiedUid;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getCurrentUid() {
        return currentUid;
    }
    public void setCurrentUid(String currentUid) {
        this.currentUid = currentUid;
    }

}