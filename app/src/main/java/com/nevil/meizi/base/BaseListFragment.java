package com.nevil.meizi.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.R;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Tangkun on 2017/5/3.
 */

public abstract class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.base_list_fragment_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.base_list_fragment_recycler)
    RecyclerView mBaseListFragmentRecycler;
    int page = 1;
    BaseQuickAdapter mAdapter;

    @Override
    protected void initView() {
        //mSwipeRefreshLayout.measure(0,0);
        mSwipeRefreshLayout.setRefreshing(true);
        loadData(1);
        mAdapter = setAdapter();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mBaseListFragmentRecycler.setAdapter(mAdapter);
        mBaseListFragmentRecycler.setLayoutManager(setLayoutManager());
        mAdapter.setOnLoadMoreListener(this, mBaseListFragmentRecycler);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }

    protected abstract void loadData(int page);

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected abstract BaseQuickAdapter setAdapter();

    @Override
    protected int setLayout() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void onRefresh() {
        page = 1;
        loadData(1);
    }

    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        mBaseListFragmentRecycler.postDelayed(() -> loadData(page + 1), 1000);
    }

    public void loadFail() {
        if (page == 1) {
            stopRefresh();
            mAdapter.setEmptyView(R.layout.view_errview);
            mAdapter.getEmptyView().setOnClickListener(v -> {
                mSwipeRefreshLayout.setRefreshing(true);
                onRefresh();
            });
        } else {
            mAdapter.loadMoreFail();
        }
    }

    public <T> void setData(List<T> list) {
        if (page != 1) {
            mAdapter.addData(list);
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.setNewData(list);
        }
        page++;
    }

}
