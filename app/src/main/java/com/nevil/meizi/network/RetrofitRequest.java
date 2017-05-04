package com.nevil.meizi.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class RetrofitRequest {
    Retrofit mRetrofit;
    GankInterface mGankInterface;


    public RetrofitRequest() {

        mRetrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl(UrlApi.ROOT_URL).build();
        mGankInterface = mRetrofit.create(GankInterface.class);
    }

    public GankInterface getGankInterface() {
        if (mGankInterface == null) {
            return null;
        }
        return mGankInterface;
    }
}
