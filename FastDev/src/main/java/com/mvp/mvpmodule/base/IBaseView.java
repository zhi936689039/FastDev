package com.mvp.mvpmodule.base;

import android.content.Context;

import com.mvp.mvpmodule.util.DialogUtil;

/**
 * @user oyzb
 * @date 2021/7/19 11:37
 */
public interface IBaseView {
    //显示等待动画
    void showLoading(boolean isCancler, String message, boolean isDealDialogDismiss, DialogUtil.LoadingDialogListener loadingDialogListener);
    void showLoading(Context context);
    //隐藏等待动画
    void hideLoading();
    void showResult(Object o, int type);
}
