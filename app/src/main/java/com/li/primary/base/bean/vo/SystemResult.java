package com.li.primary.base.bean.vo;

import com.li.primary.base.bean.BaseResult;

/**
 * Created by liu on 2017/7/13.
 */

public class SystemResult extends BaseResult{
    private SystemVOList data;

    public SystemVOList getData() {
        return data;
    }

    public void setData(SystemVOList data) {
        this.data = data;
    }
}
