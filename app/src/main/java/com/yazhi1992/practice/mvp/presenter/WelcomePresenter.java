package com.yazhi1992.practice.mvp.presenter;

import com.yazhi1992.practice.mvp.base.RxPresenter;
import com.yazhi1992.practice.mvp.presenter.contract.WelcomeContract;
import com.yazhi1992.practice.mvp.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: WelcomePresenter
 * Creator: yxc
 * date: 2016/9/22 13:17
 */
public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    private static final int COUNT_DOWN_TIME = 2200;

    public WelcomePresenter() {

    }

    @Override
    public void getWelcomeData() {
        /*Subscription rxSubscription = RetrofitHelper1.getVideoApi().getHomePage()
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(final VideoRes res) {
                        if (res != null) {
                            if (mView.isActive()) {
                                mView.showContent(res);
                            }
                        }
                        startCountDown();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        startCountDown();
                    }
                });
        addSubscribe(rxSubscription);*/

        //业务逻辑获取数据
        mView.showContent(getStringData());
        //接着则自动触发跳转过程
        startCountDown();
    }

    private void startCountDown() {
        Subscription rxSubscription = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.jumpToMain();
                    }
                });

        //注意：不在订阅的情况下要解除订阅关系
        addSubscribe(rxSubscription);
    }

    /**
     * 业务逻辑获取数据
     * @return
     */
    private String getStringData() {
        return "this is get data !";
    }

}
