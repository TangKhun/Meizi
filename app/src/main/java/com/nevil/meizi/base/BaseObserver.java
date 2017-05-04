package com.nevil.meizi.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Tangkun on 2017/5/4.
 */

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onNext(BaseEntity<T> entity) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
