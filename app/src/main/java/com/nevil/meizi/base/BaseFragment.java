package com.nevil.meizi.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nevil.meizi.view.ErrView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Tangkun on 2017/5/3.
 */

public abstract class BaseFragment extends Fragment {
    protected boolean isPrepared;
    protected View view;
    protected Unbinder unbinder;
    CompositeDisposable mCompositeDisposable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(setLayout(), container, false);
            unbinder = ButterKnife.bind(this, view);
            isPrepared = true;
            initView();
        }
        return view;
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        if (disposable != null)
            mCompositeDisposable.add(disposable);
    }

    public View getEmptyView() {
        return new ErrView(getContext());
    }

    protected abstract void initView();

    protected abstract int setLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mCompositeDisposable.clear();
    }
}
