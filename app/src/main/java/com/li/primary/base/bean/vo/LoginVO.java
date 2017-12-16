package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/20.
 */

public class LoginVO {
    private String token;
    private String facefirsturl;
    private String examYN;
    private Boolean ExamPassed;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFacefirsturl() {
        return facefirsturl;
    }

    public void setFacefirsturl(String facefirsturl) {
        this.facefirsturl = facefirsturl;
    }

    public String getExamYN() {
        return examYN;
    }

    public void setExamYN(String examYN) {
        this.examYN = examYN;
    }

    public Boolean getExamPassed() {
        return ExamPassed;
    }

    public void setExamPassed(Boolean examPassed) {
        this.ExamPassed = examPassed;
    }

}
