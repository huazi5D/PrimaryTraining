package com.li.primary.util;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.li.primary.R;

/**
 * Created by liu on 2017/6/9.
 */

public class UtilGlide {
    private static RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.loadimg_default)
            .error(R.drawable.loadimg_default)
            .priority(Priority.NORMAL);

    private static RequestOptions optionsHeader = new RequestOptions()
            .placeholder(R.mipmap.header)
            .error(R.mipmap.header)
            .priority(Priority.NORMAL);
    public static void loadImg(Fragment fragment, ImageView view, String url){
        Glide.with(fragment).load(url).apply(options).into(view);
    }

    public static void loadImg(Activity activity, ImageView view, String url){
        Glide.with(activity).load(url).apply(options).into(view);
    }

    public static void loadHeaderImg(Activity activity, ImageView view, String url){
        Glide.with(activity).load(url).apply(optionsHeader).into(view);
    }
}
