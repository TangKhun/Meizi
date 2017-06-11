package com.nevil.meizi.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.R;
import com.nevil.meizi.activity.PictureShowActivity;
import com.nevil.meizi.activity.WebActivity;
import com.nevil.meizi.adapter.GankAdapter;
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

public class GankFragment extends BaseTabListFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    int[] tabTitle = new int[]{R.string.Android, R.string.iOS, R.string.rest_video, R.string.expand_resources, R.string.web_design};
    GankAdapter mAdapter;

    @Override
    protected boolean isNeedDivider() {
        return true;
    }

    @Override
    public int[] getTabString() {
        return tabTitle;
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
                if (bean.getError().equals("false"))
                    setData(bean.getResults());
                else
                    loadFail();
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
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected BaseQuickAdapter setAdapter() {
        mAdapter = new GankAdapter(getContext(), null);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(this);
        return mAdapter;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
        Intent intent = new Intent(getContext(), PictureShowActivity.class);
        intent.putExtra("path", mAdapter.getItem(i).getImages()[0]);
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(getString(R.string.transition_name));
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(getActivity(), view, "image");
            getActivity().startActivityForResult(intent, 1000, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
        Intent intent = new Intent(getContext(), WebActivity.class);
        intent.putExtra("url", mAdapter.getItem(i).getUrl());
        intent.putExtra("TITLE", mAdapter.getItem(i).getDesc());
        startActivity(intent);
    }
}
