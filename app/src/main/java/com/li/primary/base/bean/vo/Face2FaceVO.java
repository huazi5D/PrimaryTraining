package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/27.
 */

public class Face2FaceVO {
    private float similar;
    private boolean result;
    private String errorinfo;

    public float getSimilar() {
        return similar;
    }

    public void setSimilar(float similar) {
        this.similar = similar;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo;
    }
}
