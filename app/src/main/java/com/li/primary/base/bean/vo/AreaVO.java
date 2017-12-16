package com.li.primary.base.bean.vo;

/**
 * Created by liu on 2017/6/15.
 */

public class AreaVO {
    private String TITLE3;
    private String CODE3;

    public AreaVO(String TITLE3, String CODE3) {
        this.TITLE3 = TITLE3;
        this.CODE3 = CODE3;
    }

    public String getTITLE3() {
        return TITLE3;
    }

    public void setTITLE3(String TITLE3) {
        this.TITLE3 = TITLE3;
    }

    public String getCODE3() {
        return CODE3;
    }

    public void setCODE3(String CODE3) {
        this.CODE3 = CODE3;
    }
}
