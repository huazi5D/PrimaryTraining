package com.li.primary.base.bean;

import com.li.primary.base.bean.vo.Face2FaceResult;

/**
 * Created by liu on 2017/6/27.
 */

public class FaceResult extends BaseResult{
    private Face2FaceResult data;

    public Face2FaceResult getData() {
        return data;
    }

    public void setData(Face2FaceResult data) {
        this.data = data;
    }
}
