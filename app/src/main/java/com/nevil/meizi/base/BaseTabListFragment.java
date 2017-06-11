package com.nevil.meizi.base;

import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Tangkun on 2017/5/11.
 */

public abstract class BaseTabListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, TabLayout.OnTabSelectedListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.base_tab_tab)
    TabLayout mBaseTabTab;
    @BindView(R.id.base_tab_recycle)
    RecyclerView mBaseTabRecycle;
    @BindView(R.id.base_tab_swipe)
    SwipeRefreshLayout mBaseTabSwipe;

    BaseQuickAdapter mAdapter;
    int page = 1;

    @Override
    protected void initView() {
        mBaseTabTab.setTabMode(TabLayout.GRAVITY_CENTER);
        mBaseTabTab.addOnTabSelectedListener(this);
        mBaseTabSwipe.setOnRefreshListener(this);
        int[] title = getTabString();
        for (int id : title) {
            mBaseTabTab.addTab(mBaseTabTab.newTab().setText(id));
        }
        mAdapter = setAdapter();
        mBaseTabRecycle.setAdapter(mAdapter);
        mBaseTabRecycle.setLayoutManager(setLayoutManager());
        if (isNeedDivider()){
            mBaseTabRecycle.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }

        mAdapter.setOnLoadMoreListener(this, mBaseTabRecycle);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        loadData(1, 0);
    }

    protected abstract boolean isNeedDivider();

    @Override
    protected int setLayout() {
        return R.layout.fragment_base_tab_list;
    }

    //获取TabLayout上应显示的标题
    public abstract int[] getTabString();

    protected abstract void loadData(int page, int tab);

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected abstract BaseQuickAdapter setAdapter();


    @Override
    public void onRefresh() {
        page = 1;
    }

    public void stopRefresh() {
        if (mBaseTabSwipe.isRefreshing())
            mBaseTabSwipe.setRefreshing(false);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        scrollTop();
        mBaseTabSwipe.setRefreshing(true);
        page = 1;
        loadData(page, tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onLoadMoreRequested() {
        mBaseTabRecycle.postDelayed(() -> loadData(page + 1, mBaseTabTab.getSelectedTabPosition()), 1000);
    }

    public void loadFail() {
        if (page == 1) {
            stopRefresh();
            mAdapter.setEmptyView(R.layout.view_errview);
            mAdapter.getEmptyView().setOnClickListener(v -> {
                mBaseTabSwipe.setRefreshing(true);
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

    public void scrollTop() {
        mBaseTabRecycle.smoothScrollToPosition(0);
    }

    public void setPage(int page) {
        this.page = page;
    }
}
