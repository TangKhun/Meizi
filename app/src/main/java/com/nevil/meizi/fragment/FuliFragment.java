package com.nevil.meizi.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.adapter.MeiziAdapter;
import com.nevil.meizi.base.BaseListFragment;
import com.nevil.meizi.bean.GankBean;
import com.nevil.meizi.network.Api;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class FuliFragment extends BaseListFragment {
    MeiziAdapter mAdapter;
    int page = 1;

    @Override
    protected void loadData(int page) {
        Api.getGankInterface().getFulis(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GankBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onNext(GankBean entity) {
                // Log.e("MEIZI", "onNext: " + entity.toString());
                if (entity.getError().equals("false")) {
                    mAdapter.addData(entity.getResults());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("MEIZI", "onError: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected RecyclerView.Adapter setAdapter() {
        mAdapter = new MeiziAdapter(getContext(), null);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getBaseListFragmentRecycler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData(page++);
                    }
                }, 1000);

            }
        }, getBaseListFragmentRecycler());
        return mAdapter;
    }
}
