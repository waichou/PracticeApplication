package com.yazhi1992.practice.mvp.net;


import com.yazhi1992.practice.mvp.model.http.exception.ApiException;

import rx.Subscriber;

/**
 * Created by 12262 on 2016/5/30.
 */
public abstract class MyObserver<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
//        e.printStackTrace();
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, 123));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
