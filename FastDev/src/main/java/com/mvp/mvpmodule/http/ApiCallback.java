package com.mvp.mvpmodule.http;
import io.reactivex.observers.DisposableObserver;
/**
 * 此方法实现之后无返回值
 *
 * @author Administrator
 * @date 2018/3/25
 */

public abstract class ApiCallback extends DisposableObserver<Object> {
    public abstract void onRequestError(Throwable e);
    public abstract void onRequestSuccess(Object model);
    public abstract void onRequestFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onRequestError(e);
    }

    @Override
    public void onNext(Object model) {
        onRequestSuccess(model);
    }

    @Override
    public void onComplete() {
        onRequestFinish();
    }
}
