package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/8.
 */

public class HomeNewVO {

    /**
     * id : 16
     * newsname : 新闻标题13
     * newsdetail : http://59.110.242.72:8080/yitongeduapp/news/getNewsById?id=16
     * openyn : Y
     */

    private int id;
    private String newsname;
    private String newsdetail;
    private String openyn;
    private String newsimg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsname() {
        return newsname;
    }

    public void setNewsname(String newsname) {
        this.newsname = newsname;
    }

    public String getNewsdetail() {
        return newsdetail;
    }

    public void setNewsdetail(String newsdetail) {
        this.newsdetail = newsdetail;
    }

    public String getOpenyn() {
        return openyn;
    }

    public void setOpenyn(String openyn) {
        this.openyn = openyn;
    }

    public String getNewsimg() {
        return newsimg;
    }

    public void setNewsimg(String newsimg) {
        this.newsimg = newsimg;
    }
}
