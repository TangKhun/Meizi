package com.nevil.meizi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

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
        GlideImageManager.loadImage(mContext ,BaseUrl.TNGOU_IMAGE_ROOT_URL + bean.getImg(), holder.getView(R.id.recycle_image));
        //Glide.with(mContext).load(BaseUrl.TNGOU_IMAGE_ROOT_URL + bean.getImg()).placeholder(R.drawable.glide_empty).error(R.drawable.glide_err).into((ImageView) holder.getView(R.id.recycle_image));
        holder.setOnClickListener(R.id.recycle_image, v -> {
            Intent intent = new Intent(mContext, GroupImageActivity.class);
            // intent.putExtra("path", BaseUrl.TNGOU_IMAGE_ROOT_URL + bean.getImg());
            intent.putExtra("ID", bean.getId());
            mContext.startActivity(intent);
        });
    }
}
