package com.sdacn.yinpin.network;

import com.mvp.mvpmodule.http.ApiCallback;
/**
 * @user oyzb
 * @date 2021/9/23 12:01
 */
public abstract class HttpCallBack extends ApiCallback {
    public abstract void onSuccess(Object model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    public abstract void loginByOther();
    @Override
    public void onRequestError(Throwable e) {

    }

    @Override
    public void onRequestSuccess(Object model) {
    }

    @Override
    public void onRequestFinish() {

    }
}
