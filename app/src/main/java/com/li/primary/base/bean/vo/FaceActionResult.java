package com.li.primary.base.bean.vo;

import java.util.List;

/**
 * Created by liu on 2017/6/25.
 */

public class FaceActionResult {
    private List<PointVO> points;
    private float pitch;
    private float roll;
    private float yaw;
    private float score;
    private boolean result;
    private String errorinfo;

    public List<PointVO> getPoints() {
        return points;
    }

    public void setPoints(List<PointVO> points) {
        this.points = points;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo;
    }
}
