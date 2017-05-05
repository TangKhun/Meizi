package com.nevil.meizi.network;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class BaseUrl {
    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * <p>
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     *
     * http://www.tngou.net/tnfs/api/list?page=1&rows=10
     *
     * http://www.tngou.net/tnfs/api/show?id=1035
     */

    public static final String GANK_ROOT_URL = "http://gank.io/api/data/";
    public static final String GankFuli = "福利/10/";
    public static final String TNGOU_ROOT_URL = "http://www.tngou.net/tnfs/api/";
    public static final String TNGOU_IMAGE_LIST = "list";
    public static final String TNGOU_IMAGE_SHOW = "show";

    public static final String TNGOU_IMAGE_ROOT_URL = "http://tnfs.tngou.net/img";



}
