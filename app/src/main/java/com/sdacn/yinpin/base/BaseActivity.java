package com.sdacn.yinpin.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sdacn.yinpin.common.constans.Constants;
import com.sdacn.yinpin.helper.GlideHelper;
import com.util.DialogUtil;
import com.util.LogUtil;
import com.util.ValidateUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    protected P mPresent;
    private Context mContext;
    private Dialog mLoadingDialog;
    private Dialog mNormalLoadingDialog;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder=ButterKnife.bind(this);
        mContext=this;
        if (isUseEventBus()) {
            LogUtil.e(Constants.logTag,"EventBus注册");
            EventBus.getDefault().register(this);
        }
        //初始化mPresenter
        initPresenter();
        //绑定view
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.attachView(this);
            mPresent.onCreate();
        }
        init();
        initEvent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onDestroy();
        }
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        hideLoading();
        if(ValidateUtils.isValidate(unbinder)){
            unbinder.unbind();
        }

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
    public void showLoading(Context context) {
        mNormalLoadingDialog=DialogUtil.showLoading(context);
    }

    @Override
    public void hideLoading() {
        if(ValidateUtils.isValidate(mLoadingDialog)&&mLoadingDialog.isShowing()&&!((Activity)mContext).isFinishing()){
            mLoadingDialog.dismiss();
        }
        if(ValidateUtils.isValidate(mNormalLoadingDialog)&&mNormalLoadingDialog.isShowing()&&!((Activity)mContext).isFinishing()){
            mNormalLoadingDialog.dismiss();
        }
    }

    protected Context getContext(){
        return mContext;
    }
    protected Activity getActivity(){
        return BaseActivity.this;
    }

    protected void startActivity(Class<?> targetClass) {
        GlideHelper.clearCache(getActivity());
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }

    protected void startActivity(Class<?> targetClass, String key, String value) {
        GlideHelper.clearCache(getActivity());
        Intent intent = new Intent(this, targetClass);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    protected void startActivity(Class<?> targetClass, int requestCode) {
        GlideHelper.clearCache(getActivity());
        Intent intent = new Intent(this, targetClass);
        startActivityForResult(intent, requestCode);
    }

    protected void startActivity(Class<?> targetClass, int requestCode, Bundle bundle) {
        GlideHelper.clearCache(getActivity());
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }
}
