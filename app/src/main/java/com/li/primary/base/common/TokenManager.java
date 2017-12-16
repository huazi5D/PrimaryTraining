package com.li.primary.base.common;

import android.content.Context;
import android.text.TextUtils;

import com.li.primary.base.bean.vo.InfoVO;
import com.li.primary.util.UtilSPutil;

/**
 * Created by liu on 2017/7/2.
 */

public class TokenManager {
    private static InfoVO mInfoVO;

    public static String getToken(Context context){
        String token = UtilSPutil.getInstance(context).getString("token", null);
        return token;
    }

    public static void signout(Context context){
        UtilSPutil.getInstance(context).setString("token", null);
    }

    public static void setUserInfo(InfoVO vo){
        mInfoVO = vo;
    }

    public static InfoVO getUserInfo(){
        return mInfoVO;
    }

    public static boolean isLogin(Context context){
        if(!TextUtils.isEmpty(getToken(context))){
            return true;
        }else{
            return false;
        }
    }
}
