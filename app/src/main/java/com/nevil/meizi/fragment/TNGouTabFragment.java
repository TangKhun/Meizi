package com.nevil.meizi.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.R;
import com.nevil.meizi.adapter.TNGouAdapter;
import com.nevil.meizi.base.BaseTabListFragment;
import com.nevil.meizi.bean.TNGouBean;
import com.nevil.meizi.network.BaseUrl;
import com.nevil.meizi.network.NetClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by som on 2017-06-09.
 */

public class TNGouTabFragment extends BaseTabListFragment implements BaseQuickAdapter.OnItemClickListener {
    int[] tabTitle = new int[]{R.string.sexy_beauty,R.string.japanese_beauty,R.string.silk_stockings,R.string.beauty_photos,R.string.beauty_portrait,R.string.pure_beauty,R.string.sexy_models};
    TNGouAdapter adapter;

    @Override
    protected boolean isNeedDivider() {
        return false;
    }

    @Override
    public int[] getTabString() {
        return tabTitle;
    }

    @Override
    protected void loadData(int page, int tab) {
        Log.e("MEIZI", "loadData: " + tab);
        NetClient.getInterface(BaseUrl.TNGOU_ROOT_URL).getTNGouList(page, 20, tab+1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TNGouBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onNext(TNGouBean tnGouBean) {
                if (tnGouBean.getStatus().equals("true")) {
                    setData(tnGouBean.getTngou());
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
        adapter = new TNGouAdapter(getContext(), null);
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
