package com.sdacn.yinpin.module.home;

import android.os.Bundle;
import android.widget.Button;

import com.mvp.mvpmodule.base.BaseActivity;
import com.sdacn.yinpin.R;
import com.sdacn.yinpin.module.main.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 创建人 ：ouyangSuperHandsome
 * @version 1.0
 * @package 包名 ：com.sdacn.yinpin.module.home
 * @createTime 创建时间 ：2020/5/21 0:08
 * @modifyBy 修改人 ：ouyangSuperHandsome
 * @modifyTime 修改时间 ：2020/5/21 0:08
 * @modifyMemo 修改备注：
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.tv_enter)
    Button tvEnter;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    public void showResult(Object o, int type) {

    }

    @OnClick(R.id.tv_enter)
    public void onViewClicked() {
        startActivity(MainActivity.class);
    }
}
