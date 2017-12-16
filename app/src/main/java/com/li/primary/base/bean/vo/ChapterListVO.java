package com.li.primary.base.bean.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liu on 2017/6/19.
 */

public class ChapterListVO implements Serializable{
    private List<ChapterVO> sysEduTypeList;
    private String sumEduTime;
    private long longtime;

    public List<ChapterVO> getSysEduTypeList() {
        return sysEduTypeList;
    }

    public void setSysEduTypeList(List<ChapterVO> sysEduTypeList) {
        this.sysEduTypeList = sysEduTypeList;
    }

    public String getSumEduTime() {
        return sumEduTime;
    }

    public void setSumEduTime(String sumEduTime) {
        this.sumEduTime = sumEduTime;
    }

    public long getLongtime() {
        return longtime;
    }

    public void setLongtime(long longtime) {
        this.longtime = longtime;
    }
}
