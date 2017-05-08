package com.nevil.meizi.network;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.nevil.meizi.R;
import com.nevil.meizi.util.T;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Tangkun on 2017/5/6.
 */

public class GlideImageManager {
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).placeholder(R.drawable.glide_empty).error(R.drawable.glide_err).into(imageView);
    }

    public static void loadImageNeedRequst(Context context, String url, ImageView imageView, ProgressBar progressBar) {
        Glide.with(context).load(url).error(R.drawable.glide_err).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "图片加载失败", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(imageView);
    }

    public static void downLoadImage(Context context, String url) {
        Glide.with(context).load(url).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                try {
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), resource.getPath(), "meizi", "description");
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(resource)));
                    T.showShortToast(context, "保存成功");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
