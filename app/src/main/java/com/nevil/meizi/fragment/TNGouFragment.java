package com.nevil.meizi.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.adapter.TNGouAdapter;
import com.nevil.meizi.base.BaseListFragment;
import com.nevil.meizi.bean.TNGouBean;
import com.nevil.meizi.network.BaseUrl;
import com.nevil.meizi.network.NetClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class TNGouFragment extends BaseListFragment {
    int classId = 1;

    @Override
    protected void loadData(int page) {
        NetClient.getInterface(BaseUrl.TNGOU_ROOT_URL).getTNGouList(page, 20, classId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TNGouBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onNext(TNGouBean bean) {
                if (bean.getStatus().equals("true")) {
                    setData(bean.getTngou());
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

    public void changeClassId(int classId) {
        scrollTop();
        this.classId = classId;
        setPage(1);
        loadData(1);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected BaseQuickAdapter setAdapter() {
        return new TNGouAdapter(getContext(), null);
    }
}
