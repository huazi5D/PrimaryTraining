package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/18.
 */

public class InfoVO {


    /**
     * id : 1628
     * title : 朱峰
     * tel : 18212341234
     * paperscode : 142724198408203919
     * facefirsturl : http://59.110.242.72:8080/img/userimg/06348449100000425007.png
     * cyzg_clrq : 1483200000000
     * persontype : 010600
     */

    private int id;
    private String title;
    private String tel;
    private String paperscode;
    private String facefirsturl;
    private long cyzg_clrq;
    private String persontype;
    private String examYN;

    public String getExamYN() {
        return examYN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPaperscode() {
        return paperscode;
    }

    public void setPaperscode(String paperscode) {
        this.paperscode = paperscode;
    }

    public String getFacefirsturl() {
        return facefirsturl;
    }

    public void setFacefirsturl(String facefirsturl) {
        this.facefirsturl = facefirsturl;
    }

    public long getCyzg_clrq() {
        return cyzg_clrq;
    }

    public void setCyzg_clrq(long cyzg_clrq) {
        this.cyzg_clrq = cyzg_clrq;
    }

    public String getPersontype() {
        return persontype;
    }

    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }
}
