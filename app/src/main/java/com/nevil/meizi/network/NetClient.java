package com.nevil.meizi.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nevil.meizi.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class NetClient {
    static NetInterface mGankNetInterface;
    static NetInterface mTNGouNetInterface;
    static NetInterface mInterface;


    static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl).client(getClient()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static NetInterface getInterface(String baseUrl){
        return getRetrofit(baseUrl).create(NetInterface.class);
    }

//    public static NetInterface getGankNetInterface() {
//        if (mGankNetInterface == null) {
//            mGankNetInterface = getRetrofit(UrlApi.GANK_ROOT_URL).create(NetInterface.class);
//        }
//        return mGankNetInterface;
//    }
//
//    public static NetInterface getTNGouNetInterface() {
//        if (mTNGouNetInterface == null) {
//            mTNGouNetInterface = getRetrofit(UrlApi.TNGOU_ROOT_URL).create(NetInterface.class);
//        }
//        return mTNGouNetInterface;
//    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG)
            builder.addInterceptor(new HttpLoggingInterceptor());
        builder.connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(300, TimeUnit.SECONDS);
        return builder.build();
    }

}
