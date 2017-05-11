package com.nevil.meizi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nevil.meizi.R;
import com.nevil.meizi.bean.TNGouImageDataBean;
import com.nevil.meizi.network.BaseUrl;
import com.nevil.meizi.network.GlideImageManager;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/5.
 */
public class GroupImageAdapter extends BaseQuickAdapter<TNGouImageDataBean, BaseViewHolder> {
    private Context mContext;

    public GroupImageAdapter(Context context, @Nullable List<TNGouImageDataBean> data) {
        super(R.layout.recycle_item_group_image, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, TNGouImageDataBean bean) {
        GlideImageManager.loadImageNeedRequest(mContext, BaseUrl.TNGOU_IMAGE_ROOT_URL + bean.getSrc(), holder.getView(R.id.group_image_view), holder.getView(R.id.group_image_process));
        holder.setText(R.id.group_image_text, (holder.getAdapterPosition() + 1) + "/" + getItemCount()).addOnClickListener(R.id.group_image_view).addOnLongClickListener(R.id.group_image_view);
    }

}
