package com.nevil.meizi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nevil.meizi.R;
import com.nevil.meizi.bean.GankDataBean;
import com.nevil.meizi.network.GlideImageManager;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/11.
 */

public class GankAdapter extends BaseQuickAdapter<GankDataBean, BaseViewHolder> {
    Context mContext;

    public GankAdapter(Context context, @Nullable List<GankDataBean> data) {
        super(R.layout.recycle_item_gank, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, GankDataBean bean) {
        if (bean.getImages() != null && bean.getImages().length > 0) {
            holder.setVisible(R.id.item_gank_image, true);
            GlideImageManager.loadImage(mContext, bean.getImages()[0] + "?imageView2/0/w/100", holder.getView(R.id.item_gank_image));
            holder.addOnClickListener(R.id.item_gank_image);
        } else
            holder.setVisible(R.id.item_gank_image, false);
        if (bean.getWho() != null)
            holder.setText(R.id.item_gank_author, bean.getWho());
        else
            holder.setVisible(R.id.item_gank_author, false);
        holder.setText(R.id.item_gank_title, bean.getDesc()).setText(R.id.item_gank_class, bean.getType()).setText(R.id.item_gank_time, bean.getPublishedAt().substring(0, bean.getPublishedAt()
                .indexOf("T")));
    }
}
