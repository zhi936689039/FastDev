package com.sdacn.yinpin.http;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.text.TextUtils;


import com.sdacn.yinpin.base.BaseActivity;
import com.sdacn.yinpin.common.constans.Constants;
import com.util.LogUtil;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * 此方法实现之后无返回值
 *
 * @author Administrator
 * @date 2018/3/25
 */

public abstract class ApiCallback extends DisposableObserver<Object> {

    public abstract void onSuccess(Object model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            LogUtil.d(Constants.logTag,"code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(Object model) {
        onSuccess(model);

    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
