package com.example.study.model;

public class SearchParamNoLomBok {

    private String account;
    private String email;
    private int page;

    @Override
    public String toString() {
        return "SearchParam{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", page=" + page +
                '}';
    }

    public SearchParamNoLomBok() {
    }

    public SearchParamNoLomBok(String account, String email, int page) {
        this.account = account;
        this.email = email;
        this.page = page;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
