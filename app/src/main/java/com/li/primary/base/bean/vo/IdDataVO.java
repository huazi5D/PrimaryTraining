package com.li.primary.base.bean.vo;

import java.util.List;

/**
 * Created by liu on 2017/6/15.
 */

public class IdDataVO {
    private String data;
    private List<CityVO> sysareList;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<CityVO> getSysareList() {
        return sysareList;
    }

    public void setSysareList(List<CityVO> sysareList) {
        this.sysareList = sysareList;
    }
}
