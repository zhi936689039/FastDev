package com.sdacn.yinpin.base;

/**
 * present基类
 */
public interface IBasePresent<V extends IBaseView> {
    //绑定View
    void attachView(V view);
    //释放View(防止内存泄漏)
    void detatchView();
    //获取View
    V getView();
    //是否与View绑定
    boolean isViewAttach();
}
