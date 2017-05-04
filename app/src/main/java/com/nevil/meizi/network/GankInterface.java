package com.nevil.meizi.network;

import com.nevil.meizi.bean.GankBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Tangkun on 2017/5/4.
 */

public interface GankInterface {
    @GET(UrlApi.Fuli + "{page}")
    Observable<GankBean> getFulis(@Path("page") int page);

    @GET(UrlApi.Android + "{page}")
    Observable<List<GankBean>> getAndroids(@Path("page") int page);
}

