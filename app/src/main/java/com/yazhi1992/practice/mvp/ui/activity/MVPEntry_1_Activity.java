package com.yazhi1992.practice.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yazhi1992.practice.R;
import com.yazhi1992.practice.mvp.base.BaseMvpActivity;
import com.yazhi1992.practice.mvp.presenter.WelcomePresenter;
import com.yazhi1992.practice.mvp.presenter.contract.WelcomeContract;
import com.yazhi1992.practice.mvp.utils.EventUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 开屏页
 * Creator: yxc
 * date: 2017/9/6 14:57
 */
public class MVPEntry_1_Activity extends BaseMvpActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.getDataBtn)
    Button getDataBtn;
    @BindView(R.id.requestServerDataBtn)
    Button requestServerDataBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_mvp_entry_1_layout;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new WelcomePresenter();
    }

    @Override
    protected void initView() {
        //默认加载
        mPresenter.getWelcomeData();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(String result) {
        Toast.makeText(this, "结果=" + result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void jumpToMain() {
        //start activity
        //
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.getDataBtn, R.id.requestServerDataBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getDataBtn:
                //手动触发加载，并获取数据!
                mPresenter.getWelcomeData();
                break;
            case R.id.requestServerDataBtn:
                //手动触发获取网络数据！
                startActivity(new Intent(this,MVPEntryHttpActivity.class));
                break;
        }
    }
}