package com.nevil.meizi.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nevil.meizi.R;
import com.nevil.meizi.activity.GroupImageActivity;
import com.nevil.meizi.bean.TNGouDataBean;
import com.nevil.meizi.network.BaseUrl;
import com.nevil.meizi.network.GlideImageManager;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class TNGouAdapter extends BaseQuickAdapter<TNGouDataBean, BaseViewHolder> {
    private Context mContext;

    public TNGouAdapter(Context context, @Nullable List<TNGouDataBean> data) {
        super(R.layout.recycle_item_image, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, TNGouDataBean bean) {
        GlideImageManager.loadImage(mContext, BaseUrl.TNGOU_IMAGE_ROOT_URL + bean.getImg(), holder.getView(R.id.recycle_image));
        holder.setOnClickListener(R.id.recycle_image, v -> {
            Intent intent = new Intent(mContext, GroupImageActivity.class);
            intent.putExtra("ID", bean.getId());
            if (Build.VERSION.SDK_INT >= 21) {
                ImageView imageView = holder.getView(R.id.recycle_image);
                imageView.setTransitionName(mContext.getString(R.string.transition_name));
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) mContext, imageView, "image");
                ((Activity) mContext).startActivityForResult(intent, 1000, options.toBundle());
            } else {
                mContext.startActivity(intent);
            }

        });
    }
}
