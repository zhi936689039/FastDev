package com.sdacn.yinpin.base;

/**
 * present基类
 */
public interface IBasePresent<V extends IBaseView>{

    //生命周期管理
    void onCreate();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void attachView(V view);
    //获取View
    V getView();
    //是否与View绑定
    boolean isViewAttach();
}
