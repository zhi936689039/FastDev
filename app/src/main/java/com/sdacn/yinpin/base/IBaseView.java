package com.sdacn.yinpin.base;

import android.content.Context;

import com.util.DialogUtil;

/**
 * 基础View
 */
public interface IBaseView {
    //显示等待动画
    void showLoading(boolean isCancler, String message, boolean isDealDialogDismiss, DialogUtil.LoadingDialogListener loadingDialogListener);
    void showLoading(Context context);
    //隐藏等待动画
    void hideLoading();
    void showResult(Object o,int type);
}
