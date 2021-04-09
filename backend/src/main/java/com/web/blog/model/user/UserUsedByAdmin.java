package com.web.blog.model.user;

public class UserUsedByAdmin {
    private String uid;
    private String email;
    private String create_date;
    private String lastModify;

    public UserUsedByAdmin() {
    }

    public UserUsedByAdmin(String uid, String email, String create_date, String lastModify) {
        this.uid = uid;
        this.email = email;
        this.create_date = create_date;
        this.lastModify = lastModify;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreate_date() {
        return create_date;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getLastModify() {
        return lastModify;
    }
    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }
}