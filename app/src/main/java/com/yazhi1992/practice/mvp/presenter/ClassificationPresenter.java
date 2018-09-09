package com.yazhi1992.practice.mvp.presenter;

import com.yazhi1992.practice.mvp.base.RxPresenter;
import com.yazhi1992.practice.mvp.business.RetrofitUtils;
import com.yazhi1992.practice.mvp.model.bean.UserBean;
import com.yazhi1992.practice.mvp.model.http.exception.ApiException;
import com.yazhi1992.practice.mvp.net.HttpMethods;
import com.yazhi1992.practice.mvp.net.MyObserver;
import com.yazhi1992.practice.mvp.presenter.contract.ClassificationContract;

/**
 * Description: ClassificationPresenter
 * Creator: yxc
 * date: 2016/9/21 17:55
 */
public class ClassificationPresenter extends RxPresenter<ClassificationContract.View> implements ClassificationContract.Presenter {
    int page = 0;

    public ClassificationPresenter() {
    }

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
        new RetrofitUtils().queryClassification(new MyObserver<UserBean>() {
                    @Override
                    protected void onError(ApiException ex) {
                        mView.refreshFaild(ex.getDisplayMessage());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onNext(UserBean res) {
                        if (res != null) {
                            mView.showContent(res);
                        }
                    }
                });
    }
}
