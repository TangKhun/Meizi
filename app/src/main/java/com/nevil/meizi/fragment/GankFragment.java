package com.nevil.meizi.fragment;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.R;
import com.nevil.meizi.base.BaseTabListFragment;
import com.nevil.meizi.bean.GankBean;
import com.nevil.meizi.network.BaseUrl;
import com.nevil.meizi.network.NetClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tangkun on 2017/5/11.
 */

public class GankFragment extends BaseTabListFragment {
    int[] tabTitle = new int[]{R.string.fuli, R.string.Android, R.string.iOS, R.string.rest_video, R.string.expand_resources, R.string.web_design};


    @Override
    public int[] getTabString() {
        return tabTitle;
    }

    @Override
    public void tabChange() {

    }

    @Override
    protected void loadData(int page, int tab) {
        NetClient.getInterface(BaseUrl.GANK_ROOT_URL).getGank(getResources().getString(tabTitle[tab]), page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GankBean>() {


            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onNext(GankBean bean) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return null;
    }

    @Override
    protected BaseQuickAdapter setAdapter() {
        return null;
    }
}
