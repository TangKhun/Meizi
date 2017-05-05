package com.nevil.meizi.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by huangxue on 2016/7/14.
 */
public class T {
    public static void showShortToast(Context context,String text){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(Context context,String text){
        Toast.makeText(context,text, Toast.LENGTH_LONG).show();
    }
}
