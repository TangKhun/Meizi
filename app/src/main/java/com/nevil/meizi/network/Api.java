package com.nevil.meizi.network;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class Api {
    private static Api api;
    private static GankInterface mGankInterface;
    protected static final Object monitor = new Object();

//    public static Api getInstance() {
//        synchronized (Api.class) {
//            if (api == null)
//                api = new Api();
//        }
//        return api;
//    }

    public static  GankInterface getGankInterface() {
        synchronized (monitor) {
            if (mGankInterface == null)
                mGankInterface = NetClicent.getGankInterface();
        }
        return mGankInterface;
    }

    private Api() {
        //mGankInterface = NetClicent.getRetrofit().create(GankInterface.class);
    }

}
