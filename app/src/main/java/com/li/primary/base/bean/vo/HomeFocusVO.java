package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/8.
 */

public class HomeFocusVO {


    /**
     * id : 1
     * cfname : 轮播图1
     * cfdesc : 轮播图1的描述
     * imgurl : http://59.110.242.72:8080/img/userimg/1.jpg
     * urltype : 0
     * cfurl : null
     * datelastmaint : 1496755394000
     */

    private int id;
    private String cfname;
    private String cfdesc;
    private String imgurl;
    private String urltype;
    private String cfurl;
    private long datelastmaint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCfname() {
        return cfname;
    }

    public void setCfname(String cfname) {
        this.cfname = cfname;
    }

    public String getCfdesc() {
        return cfdesc;
    }

    public void setCfdesc(String cfdesc) {
        this.cfdesc = cfdesc;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getUrltype() {
        return urltype;
    }

    public void setUrltype(String urltype) {
        this.urltype = urltype;
    }

    public String getCfurl() {
        return cfurl;
    }

    public void setCfurl(String cfurl) {
        this.cfurl = cfurl;
    }

    public long getDatelastmaint() {
        return datelastmaint;
    }

    public void setDatelastmaint(long datelastmaint) {
        this.datelastmaint = datelastmaint;
    }
}
