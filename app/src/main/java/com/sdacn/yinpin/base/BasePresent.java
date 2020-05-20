package com.sdacn.yinpin.base;

import com.sdacn.yinpin.http.ApiCallback;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * present基类
 * @param <V>
 */
public abstract class BasePresent<V extends IBaseView> implements IBasePresent<V> {
    private WeakReference<V> mvpView;//获取V层的View
    private CompositeDisposable mCompositeDisposable;
    @Override
    public void attachView(V view) {
        this.mvpView=new WeakReference<>(view);
    }

    @Override
    public void detatchView() {
        if (mvpView != null) {
            mvpView.clear();
            mvpView=null;
        }
        onUnSubscribe();
    }

    @Override
    public V getView() {
        return mvpView.get();
    }

    @Override
    public boolean isViewAttach() {
        return mvpView!=null;
    }

    //RxJava取消观察者，防止内存泄漏
    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


    /**
     * 添加观察者
     * @param observable   调用的接口
     * @param type         请求类型 自定义的
     */
    public void addSubscription(Observable observable,int type) {
        ApiCallback apiCallback=new ApiCallback() {
            @Override
            public void onSuccess(Object model) {
                onSuccessRequest(type,model);
            }

            @Override
            public void onFailure(String msg) {
                onFail(msg);
            }

            @Override
            public void onFinish() {
                onComplete();
            }
        };
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(apiCallback);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(apiCallback);
    }
    protected abstract void onSuccessRequest(int type,Object model);
    protected abstract void onFail(String msg);
    protected abstract void onComplete();
}
