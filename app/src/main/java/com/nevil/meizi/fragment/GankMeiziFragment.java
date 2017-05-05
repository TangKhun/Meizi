package com.nevil.meizi.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.adapter.GankMeiziAdapter;
import com.nevil.meizi.base.BaseListFragment;
import com.nevil.meizi.bean.GankBean;
import com.nevil.meizi.network.BaseUrl;
import com.nevil.meizi.network.NetClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class GankMeiziFragment extends BaseListFragment {
    @Override
    protected void loadData(final int page) {
        NetClient.getInterface(BaseUrl.GANK_ROOT_URL).getGankMeizi(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GankBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onNext(GankBean entity) {
                if (entity.getError().equals("false")) {
                    setData(entity.getResults());
                } else {
                    loadFail();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                loadFail();
            }

            @Override
            public void onComplete() {
                stopRefresh();
            }
        });
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected BaseQuickAdapter setAdapter() {
        return new GankMeiziAdapter(getContext(), null);
    }

}
