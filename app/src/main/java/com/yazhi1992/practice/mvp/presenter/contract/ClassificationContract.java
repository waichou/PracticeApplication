package com.yazhi1992.practice.mvp.presenter.contract;


import com.yazhi1992.practice.mvp.base.BasePresenter;
import com.yazhi1992.practice.mvp.base.BaseView;
import com.yazhi1992.practice.mvp.model.bean.UserBean;

/**
 * Description: ClassificationContract
 * Creator: yxc
 * date: 2016/9/21 17:55
 */
public interface ClassificationContract {

    interface View extends BaseView {

        void showContent(UserBean userBean);

        void refreshFaild(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();
    }
}
