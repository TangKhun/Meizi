package com.nevil.meizi.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nevil.meizi.R;
import com.nevil.meizi.activity.PictureShowActivity;
import com.nevil.meizi.bean.GankDataBean;
import com.nevil.meizi.network.GlideImageManager;
import com.nevil.meizi.util.GlidePalette;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/3.
 */

public class GankMeiziAdapter extends BaseQuickAdapter<GankDataBean, BaseViewHolder> {
    private Context mContext;

    public GankMeiziAdapter(Context context, @Nullable List<GankDataBean> data) {
        super(R.layout.recycle_item_image, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder holder, GankDataBean gankDataBean) {
        GlideImageManager.loadImage(mContext, gankDataBean.getUrl(), holder.getView(R.id.recycle_image));
        holder.setOnClickListener(R.id.recycle_image, v -> {
            Intent intent = new Intent(mContext, PictureShowActivity.class);
            intent.putExtra("path", gankDataBean.getUrl());
//            if (Build.VERSION.SDK_INT >= 21) {
//                ImageView imageView = holder.getView(R.id.recycle_image);
//                imageView.setTransitionName(mContext.getString(R.string.transition_name));
//                ActivityOptions options = ActivityOptions
//                        .makeSceneTransitionAnimation((Activity) mContext, imageView, "image");
//                ((Activity) mContext).startActivityForResult(intent, 1000, options.toBundle());
//            } else {
                mContext.startActivity(intent);
            //}
        });
    }

}
