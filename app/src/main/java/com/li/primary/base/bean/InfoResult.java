package com.li.primary.base.bean;

import com.li.primary.base.bean.vo.InfoVO;

/**
 * Created by liu on 2017/6/18.
 */

public class InfoResult extends BaseResult{
    private InfoVO data;

    public InfoVO getData() {
        return data;
    }

    public void setData(InfoVO data) {
        this.data = data;
    }
}
