package com.yazhi1992.practice.mvp.base;

/**
 * Description: BasePresenter
 * Creator: yxc
 * date: 2016/9/21 10:42
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
