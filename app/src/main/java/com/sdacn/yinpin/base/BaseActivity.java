package com.sdacn.yinpin.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sdacn.yinpin.common.constans.Constants;
import com.util.DialogUtil;
import com.util.LogUtil;
import com.util.ValidateUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：CopyoOfTheAudio
 * @createTime 创建时间 ：2020/5/19 18:58
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2020/5/19 18:58
 * @modifyMemo 修改备注：
 */
public  abstract class BaseActivity<P extends IBasePresent> extends AppCompatActivity implements IBaseView {
    private P mPresent;
    private Context mContext;
    private Dialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
        mContext=this;
        if (isUseEventBus()) {
            LogUtil.e(Constants.logTag,"EventBus注册");
            EventBus.getDefault().register(this);
        }
        //初始化mPresenter
        initPresenter();
        //绑定view
        if(mPresent != null){
            mPresent.attachView(this);
        }
        init();
        initEvent();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.detatchView();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        hideLoading();
    }

    protected abstract void initPresenter();
    protected abstract void init();
    protected abstract void initEvent();
    protected abstract int getLayoutId();
    protected abstract boolean isUseEventBus();
    @Override
    public void showLoading(boolean isCancler, String message, boolean isDealDialogDismiss, DialogUtil.LoadingDialogListener loadingDialogListener) {
        if(isDealDialogDismiss){
            mLoadingDialog= DialogUtil.showLoadingDialog(mContext,message, isCancler, isDealDialogDismiss, loadingDialogListener);
        }else{
            mLoadingDialog= DialogUtil.showLoadingDialog(mContext,message, isCancler, isDealDialogDismiss,null);
        }
    }

    @Override
    public void hideLoading() {
        if(ValidateUtils.isValidate(mLoadingDialog)&&mLoadingDialog.isShowing()&&!((Activity)mContext).isFinishing()){
            mLoadingDialog.dismiss();
        }
    }

    protected Context getContext(){
        return mContext;
    }
}
