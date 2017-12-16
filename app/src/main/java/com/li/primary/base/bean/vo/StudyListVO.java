package com.li.primary.base.bean.vo;

import java.util.List;

/**
 * Created by liu on 2017/6/17.
 */

public class StudyListVO {
    private String cycle;
    private String sumEduTime;
    private String longtime;
    private List<StudyVO> sysEduTypeList;
    private String persontype;

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getSumEduTime() {
        return sumEduTime;
    }

    public void setSumEduTime(String sumEduTime) {
        this.sumEduTime = sumEduTime;
    }

    public String getLongtime() {
        return longtime;
    }

    public void setLongtime(String longtime) {
        this.longtime = longtime;
    }

    public List<StudyVO> getSysEduTypeList() {
        return sysEduTypeList;
    }

    public void setSysEduTypeList(List<StudyVO> sysEduTypeList) {
        this.sysEduTypeList = sysEduTypeList;
    }

    public String getPersontype() {
        return persontype;
    }

    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }
}
