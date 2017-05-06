package com.nevil.meizi.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nevil.meizi.R;
import com.nevil.meizi.adapter.GroupImageAdapter;
import com.nevil.meizi.base.BaseActivity;
import com.nevil.meizi.bean.TNGouImageBean;
import com.nevil.meizi.network.BaseUrl;
import com.nevil.meizi.network.NetClient;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class GroupImageActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemChildLongClickListener {

    @BindView(R.id.group_image)
    RecyclerView mGroupImageRecycle;

    GroupImageAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_group_image;
    }

    @Override
    protected void initView() {
        int id = getIntent().getIntExtra("ID", 0);
        Log.e("MEIZI", "initView: id=" + id);
        mGroupImageRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new GroupImageAdapter(this, null);
        mGroupImageRecycle.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);
        new PagerSnapHelper().attachToRecyclerView(mGroupImageRecycle);
        if (id != 0) {
            loadData(id);
        } else {
            Toast.makeText(this, "获取详情失败", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void loadData(int id) {
        NetClient.getInterface(BaseUrl.TNGOU_ROOT_URL).getTNGouImageShow(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TNGouImageBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onNext(TNGouImageBean bean) {
                Log.e("MEIZI", "onNext: " + bean.toString());
                mAdapter.setNewData(bean.getList());
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
        finish();
    }

    @Override
    public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int i) {
        Toast.makeText(this, "position=" + i, Toast.LENGTH_SHORT).show();
        return false;
    }
}
