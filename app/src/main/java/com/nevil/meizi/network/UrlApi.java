package com.nevil.meizi.network;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class UrlApi {
    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * <p>
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */

    public static final String ROOT_URL = "http://gank.io/api/data/";

    static final String Fuli = "福利/10/";
    static final String Android = "Android/10/";
    static final String iOS = "iOS/10/";
    static final String relax = "休息视频/10/";
}
