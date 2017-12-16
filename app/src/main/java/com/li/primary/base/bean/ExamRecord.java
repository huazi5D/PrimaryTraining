package com.li.primary.base.bean;

import com.li.primary.base.bean.vo.ExamRecordVO;

import java.util.List;

/**
 * Created by liu on 2017/6/30.
 */

public class ExamRecord {
    private List<ExamRecordVO> list;
    private int pagesize;
    private int count;
    private int lastpagenum;
    private int pagenum;

    public List<ExamRecordVO> getList() {
        return list;
    }

    public void setList(List<ExamRecordVO> list) {
        this.list = list;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLastpagenum() {
        return lastpagenum;
    }

    public void setLastpagenum(int lastpagenum) {
        this.lastpagenum = lastpagenum;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }
}
