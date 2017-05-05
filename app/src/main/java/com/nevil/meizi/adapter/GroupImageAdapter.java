package com.nevil.meizi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nevil.meizi.R;
import com.nevil.meizi.bean.TNGouImageDataBean;
import com.nevil.meizi.network.BaseUrl;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/5.
 */
public class GroupImageAdapter extends BaseQuickAdapter<TNGouImageDataBean, BaseViewHolder> {
    Context mContext;

    public GroupImageAdapter(Context context, @Nullable List<TNGouImageDataBean> data) {
        super(R.layout.recycle_item_group_image, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, TNGouImageDataBean bean) {
        Glide.with(mContext).load(BaseUrl.TNGOU_IMAGE_ROOT_URL + bean.getSrc()).placeholder(R.drawable.glide_empty).error(R.drawable.glide_err).into((ImageView) holder.getView(R.id.group_image_view));
    }
}
