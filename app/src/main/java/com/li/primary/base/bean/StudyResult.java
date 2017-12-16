package com.li.primary.base.bean;

import com.li.primary.base.bean.vo.StudyListVO;

/**
 * Created by liu on 2017/6/17.
 */

public class StudyResult extends BaseResult{
    private StudyListVO data;

    public StudyListVO getData() {
        return data;
    }

    public void setData(StudyListVO data) {
        this.data = data;
    }
}
