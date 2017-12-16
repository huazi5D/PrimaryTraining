package com.li.primary.base;

import android.os.Environment;

import com.li.primary.base.bean.vo.PlayUploadVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2017/6/13.
 */

public class AppData {
    public static String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/education/";

    public static String token = "";

    public static String cycle_code = "1";
    //考试题组别
    public static String paperid = "";
    //上传学时的sessionid
    public static String sessionid = "";
    /**
     * 是否有效
     */
    public static boolean isValid = false;

    public static List<PlayUploadVO> data = new ArrayList<>();

    public static String url = "";
    public static String examYN = "";
}
