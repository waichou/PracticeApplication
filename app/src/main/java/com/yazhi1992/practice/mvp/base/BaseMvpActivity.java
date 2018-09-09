package com.yazhi1992.practice.mvp.base;

import android.os.Bundle;


/**
 * Description: MVP Activity基类
 * Creator: yxc
 * date: 17/9/14
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected T mPresenter;

    protected abstract void createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
