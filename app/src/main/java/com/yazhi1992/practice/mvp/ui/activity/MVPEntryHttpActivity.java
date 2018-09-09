package com.yazhi1992.practice.mvp.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.yazhi1992.practice.R;
import com.yazhi1992.practice.mvp.base.BaseMvpActivity;
import com.yazhi1992.practice.mvp.business.RetrofitUtils;
import com.yazhi1992.practice.mvp.model.bean.UserBean;
import com.yazhi1992.practice.mvp.model.http.exception.ApiException;
import com.yazhi1992.practice.mvp.net.MyObserver;
import com.yazhi1992.practice.mvp.presenter.ClassificationPresenter;
import com.yazhi1992.practice.mvp.presenter.contract.ClassificationContract;

public class MVPEntryHttpActivity extends BaseMvpActivity<ClassificationPresenter> implements ClassificationContract.View {

    @Override
    protected void createPresenter() {
        mPresenter = new ClassificationPresenter();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(UserBean userBean) {
        ToastUtils.showShort("用户信息=" + userBean);
    }

    @Override
    public void refreshFaild(String msg) {
        ToastUtils.showShort("出现错误=" + msg);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_mvp_retrofit_layout;
    }

    //get network data
    public void getNetWorkData(View view){
//        RetrofitUtils.mRetrofit.queryClassification(new MyObserver<UserBean>() {
//            @Override
//            protected void onError(ApiException ex) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onNext(UserBean userBean) {
//
//            }
//        });


        mPresenter.onRefresh();
    }
}
