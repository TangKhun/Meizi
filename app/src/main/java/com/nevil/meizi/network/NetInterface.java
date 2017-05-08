package com.nevil.meizi.network;

import com.nevil.meizi.bean.GankBean;
import com.nevil.meizi.bean.TNGouBean;
import com.nevil.meizi.bean.TNGouImageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tangkun on 2017/5/4.
 */

public interface NetInterface {
    @GET(BaseUrl.GankFuli + "{page}")
    Observable<GankBean> getGankMeizi(@Path("page") int page);

    @GET(BaseUrl.TNGOU_IMAGE_LIST)
    Observable<TNGouBean> getTNGouList(@Query("page") int page, @Query("rows") int rows, @Query("id") int id);

    @GET(BaseUrl.TNGOU_IMAGE_SHOW)
    Observable<TNGouImageBean> getTNGouImageShow(@Query("id") int id);


}

