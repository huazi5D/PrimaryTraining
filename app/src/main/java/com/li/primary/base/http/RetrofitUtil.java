package com.li.primary.base.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liu on 2017/6/7.
 */

public class RetrofitUtil {
    public static Retrofit retrofit = null;
    public static Retrofit faceRetrofit = null;
    private static final long DEFAULT_TIMEOUT = 20 * 60;

    public static Retrofit getInstance() {
        if (retrofit == null) {
//            Gson gson = new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd")
//                    .create();
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder().baseUrl("http://60.222.232.21:8085/taxeducation/").
                    addConverterFactory(GsonConverterFactory.create()).
                    addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(client).build();
        }
        return retrofit;
    }


    public static Retrofit getFaceInstance() {
        if (faceRetrofit == null) {
            faceRetrofit = new Retrofit.Builder().baseUrl("http://60.222.232.22:8090/").
                    addConverterFactory(GsonConverterFactory.create()).
                    addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }
        return faceRetrofit;
    }
}
