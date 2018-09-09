package com.yazhi1992.practice.mvp.business;

import com.yazhi1992.practice.mvp.model.bean.UserBean;
import com.yazhi1992.practice.mvp.model.http.api.VideoApis;
import com.yazhi1992.practice.mvp.net.HttpMethods;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author zhouwei
 * @date 2018/9/7
 * @time 22:40
 * @desc
 */
public class RetrofitUtils {

    public static RetrofitUtils mRetrofit = new RetrofitUtils();

    private VideoApis mService = HttpMethods.getInstance().getRetrofit().create(VideoApis.class);
    /**
     * ClassificationPresenter
     *
     * @return
     */
    public void queryClassification(Subscriber<UserBean> subscriber) {
        mService.getHomePage()
                .map(new HttpMethods.ServerResultFunc<UserBean>())
                .onErrorResumeNext(new HttpMethods.HttpResultFunc<UserBean>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
