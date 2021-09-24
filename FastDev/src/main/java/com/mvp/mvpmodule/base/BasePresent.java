package com.mvp.mvpmodule.base;
import java.lang.ref.WeakReference;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import com.mvp.mvpmodule.http.ApiCallback;
import com.mvp.mvpmodule.util.LogUtil;

/**
 * present基类
 */
public abstract class BasePresent implements IBasePresent{
    private WeakReference<IBaseView> mvpView;//获取V层的View
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {
        onUnSubscribe();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        detatchView();
    }

    @Override
    public void attachView(IBaseView view) {
        this.mvpView=new WeakReference<>(view);
    }

    private void detatchView() {
        if (mvpView != null) {
            mvpView.clear();
            mvpView=null;
        }

    }

    @Override
    public IBaseView getView() {
        return mvpView.get();
    }

    @Override
    public boolean isViewAttach() {
        return mvpView!=null;
    }

    //RxJava取消观察者，防止内存泄漏
    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            LogUtil.e("请求","取消订阅");
            mCompositeDisposable.dispose();
            mCompositeDisposable=null;
        }
    }


    /**
     * 添加观察者
     * @param observable   调用的接口
     * @param type         请求类型 自定义的
     */
    public void addSubscription(Observable observable,int type) {
        if (mCompositeDisposable == null) {
            //创建观察者
            LogUtil.e("请求","创建观察者");
            mCompositeDisposable = new CompositeDisposable();
        }
        //添加观察者
        LogUtil.e("请求","创建观察者");
        mCompositeDisposable.add(getApiCallBack(type));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getApiCallBack(type));
    }
    //添加网络请求后的回调处理类
    protected abstract ApiCallback getApiCallBack(int type);
    protected abstract void onSuccessRequest(int type, Object model);
    protected abstract void onFail(String msg);
    protected abstract void onRequestComplete();
    protected abstract void loginRemote();
    protected abstract void onFail(String msg,int type);
}
