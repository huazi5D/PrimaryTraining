package com.li.primary.base.bean.vo;

import java.util.List;

/**
 * Created by liu on 2017/6/8.
 */

public class HomeVO {
    private int pagesize;
    private int newssize;
    private int pagenum;
    private int lastpagenum;
    private List<HomeNewVO> newsList;
    private List<HomeFocusVO> focusList;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getNewssize() {
        return newssize;
    }

    public void setNewssize(int newssize) {
        this.newssize = newssize;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getLastpagenum() {
        return lastpagenum;
    }

    public void setLastpagenum(int lastpagenum) {
        this.lastpagenum = lastpagenum;
    }

    public List<HomeNewVO> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<HomeNewVO> newsList) {
        this.newsList = newsList;
    }

    public List<HomeFocusVO> getFocusList() {
        return focusList;
    }

    public void setFocusList(List<HomeFocusVO> focusList) {
        this.focusList = focusList;
    }
}
