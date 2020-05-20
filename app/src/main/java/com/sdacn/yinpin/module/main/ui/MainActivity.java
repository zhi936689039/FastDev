package com.sdacn.yinpin.module.main.ui;

import com.sdacn.yinpin.R;
import com.sdacn.yinpin.base.BaseActivity;
import com.sdacn.yinpin.module.main.present.MainPresent;

import static com.sdacn.yinpin.module.main.present.MainPresent.AUDIO_TYPE;

public class MainActivity extends BaseActivity<MainPresent>{
    @Override
    protected void initPresenter() {
        mPresent=new MainPresent();
    }

    @Override
    protected void init() {
        showLoading(getContext());
        mPresent.getAudioType();
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
        switch (type){
            case AUDIO_TYPE:
                break;
        }
    }
}
