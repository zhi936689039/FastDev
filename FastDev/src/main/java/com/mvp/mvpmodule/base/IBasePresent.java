package com.mvp.mvpmodule.base;

/**
 * @user oyzb
 * @date 2021/7/19 11:37
 */
public interface IBasePresent{

    //生命周期管理
    void onCreate();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void attachView(IBaseView view);
    //获取View
    IBaseView getView();
    //是否与View绑定
    boolean isViewAttach();
}
