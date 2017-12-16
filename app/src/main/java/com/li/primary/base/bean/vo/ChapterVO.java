package com.li.primary.base.bean.vo;

import java.io.Serializable;

/**
 * Created by liu on 2017/6/19.
 */

public class ChapterVO implements Serializable{

    /**
     * code1 : 01
     * code2 : 01
     * title2 : 国家与行业的相关法规政策
     * pic : http://59.110.242.72:8080/img/video/4_4_4_4.swf
     * finishing : Y
     * finished : N
     */

    private String code1;
    private String code2;
    private String title2;
    private String pic;
    private String finishing;
    private String finished;
    private String videoTime;

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getFinishing() {
        return finishing;
    }

    public void setFinishing(String finishing) {
        this.finishing = finishing;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }
}
