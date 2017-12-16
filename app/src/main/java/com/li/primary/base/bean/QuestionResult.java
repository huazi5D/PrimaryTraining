package com.li.primary.base.bean;

import com.li.primary.base.bean.vo.QuestionVO;

/**
 * Created by liu on 2017/6/7.
 */

public class QuestionResult extends BaseResult{
    private QuestionVO data;

    public QuestionVO getData() {
        return data;
    }

    public void setData(QuestionVO data) {
        this.data = data;
    }
}
