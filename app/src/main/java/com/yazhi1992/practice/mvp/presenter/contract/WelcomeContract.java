package com.yazhi1992.practice.mvp.presenter.contract;

import com.yazhi1992.practice.mvp.base.BasePresenter;
import com.yazhi1992.practice.mvp.base.BaseView;

import java.util.List;

/**
 * Description: WelcomeContract
 * Creator: yxc
 * date: 2016/9/22 13:16
 */
public interface WelcomeContract {

    interface View extends BaseView {

        void showContent(String list);

        void jumpToMain();
    }

    interface Presenter extends BasePresenter<View> {
        void getWelcomeData();
    }
}
