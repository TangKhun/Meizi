package com.nevil.meizi.base;

import android.support.v7.widget.RecyclerView;

import com.nevil.meizi.R;

import butterknife.BindView;

/**
 * Created by Tangkun on 2017/5/3.
 */

public abstract class BaseListFragment extends BaseFragment {


    @BindView(R.id.base_list_fragment_recycler)
    RecyclerView mBaseListFragmentRecycler;
    @Override
    protected void initView() {
        loadData(1);
        mBaseListFragmentRecycler.setAdapter(setAdapter());
        mBaseListFragmentRecycler.setLayoutManager(setLayoutManager());
    }

    protected abstract void loadData(int page);

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected abstract RecyclerView.Adapter setAdapter();

    public RecyclerView getBaseListFragmentRecycler() {
        return mBaseListFragmentRecycler;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_base_list;
    }

}
