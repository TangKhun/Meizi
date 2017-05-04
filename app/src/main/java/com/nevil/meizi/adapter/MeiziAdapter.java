package com.nevil.meizi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nevil.meizi.R;
import com.nevil.meizi.bean.GankDataBean;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/3.
 */

public class MeiziAdapter extends BaseQuickAdapter<GankDataBean, BaseViewHolder> {
    private Context mContext;

    public MeiziAdapter(Context context, @Nullable List<GankDataBean> data) {
        super(R.layout.recycle_item_image, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder holder, GankDataBean gankDataBean) {
        Glide.with(mContext).load(gankDataBean.getUrl()).placeholder(R.drawable.glide_empty).error(R.drawable.glide_err).into((ImageView) holder.getView(R.id.recycle_image));
        holder.setText(R.id.recycle_text, gankDataBean.getWho()).addOnClickListener(R.id.recycle_image);
    }

}
