package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/30.
 */

public class ExamRecordVO {
    private int id;
    private long adddate;
    private String personuserid;
    private int score;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAdddate() {
        return adddate;
    }

    public void setAdddate(long adddate) {
        this.adddate = adddate;
    }

    public String getPersonuserid() {
        return personuserid;
    }

    public void setPersonuserid(String personuserid) {
        this.personuserid = personuserid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
