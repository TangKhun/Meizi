package com.nevil.meizi.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nevil.meizi.R;
import com.nevil.meizi.bean.GankDataBean;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/11.
 */

public class GankAdapter extends BaseQuickAdapter<GankDataBean, BaseViewHolder> {
    public GankAdapter(@Nullable List<GankDataBean> data) {
        super(R.layout.recycle_item_gank, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, GankDataBean bean) {

    }
}
