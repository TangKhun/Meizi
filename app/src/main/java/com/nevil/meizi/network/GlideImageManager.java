package com.nevil.meizi.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.nevil.meizi.R;
import com.nevil.meizi.util.GlidePalette;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;

/**
 * Created by Tangkun on 2017/5/6.
 */

public class GlideImageManager {
    private static RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.empty_image)
            .error(R.drawable.empty_image_err);


    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);

        //           .listener(GlidePalette.with(gankDataBean.getUrl())
        //                .use(GlidePalette.Profile.MUTED_DARK)
        //                .intoBackground(holder.getView(R.id.recycle_image)))
    }


    public static void loadImageNeedRequest(Context context, String url, ImageView imageView, AVLoadingIndicatorView progressBar) {
        //progressBar.show();
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.hide();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.hide();
                        return false;
                    }
                })
                .apply(options)
                .into(imageView);


//        Glide.with(context).load(url).error(R.drawable.empty_image_err).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                progressBar.hide();
//                //Toast.makeText(context, "图片加载失败", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                //Log.e("MEIZI", "onResourceReady: " + "isFromMemoryCache=" + isFromMemoryCache + ",isFirstResource=" + isFirstResource);
//                progressBar.hide();
//                return false;
//            }
//        }).into(imageView);

    }

    public static void setWallPaper(Context context, String url) {
        Log.e("MEIZI", "setWallPaper: " + url);
//        Glide.with(context).load(url).downloadOnly(new SimpleTarget<File>() {
//            @Override
//            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
//                Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.putExtra("mimeType", "image/*");
//                try {
//                    Uri uri = Uri.parse(MediaStore.Images.Media
//                            .insertImage(context.getContentResolver(), resource.getPath(), null, null));
//                    intent.setData(uri);
//                    ((Activity) context).startActivityForResult(intent, 10086);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                T.showShortToast(context, "");
//                Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.putExtra("mimeType", "image/*");
//                Uri uri = Uri.parse(MediaStore.Images.Media
//                        .insertImage(context.getContentResolver(), resource, null, null));
//                intent.setData(uri);
//                ((Activity) context).startActivityForResult(intent, 10086);
//            }
//        });

        Glide.with(context).download(url).into(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, Transition<? super File> transition) {

            }
        });

    }

}
