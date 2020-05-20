package com.sdacn.yinpin;

import androidx.multidex.MultiDexApplication;

/**
 * @author 创建人 ：ouyangSuperHandsome
 * @version 1.0
 * @package 包名 ：com.sdacn.yinpin
 * @createTime 创建时间 ：2020/5/19 22:22
 * @modifyBy 修改人 ：ouyangSuperHandsome
 * @modifyTime 修改时间 ：2020/5/19 22:22
 * @modifyMemo 修改备注：
 */
public class App extends MultiDexApplication {
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static App getInstance(){
        return mApp;
    }
}
