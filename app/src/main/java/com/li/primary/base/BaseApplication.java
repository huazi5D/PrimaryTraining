package com.li.primary.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.li.primary.util.UtilSPutil;

/**
 * Created by liu on 2017/6/20.
 */

public class BaseApplication extends Application{

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        AppData.token = UtilSPutil.getInstance(this).getString("token", "");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // android 7.0系统解决拍照的问题
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }
}
