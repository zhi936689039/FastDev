package com.sdacn.yinpin.module.main.api;

import com.sdacn.yinpin.bean.home.AudioTypeBean;
import com.sdacn.yinpin.http.RetrofitServiceManager;

import io.reactivex.Observable;

/**
 * @author 创建人 ：ouyangSuperHandsome
 * @version 1.0
 * @package 包名 ：com.sdacn.yinpin.module.main.api
 * @createTime 创建时间 ：2020/5/21 1:56
 * @modifyBy 修改人 ：ouyangSuperHandsome
 * @modifyTime 修改时间 ：2020/5/21 1:56
 * @modifyMemo 修改备注：
 */
public class MainApisImpl {
    private MainApi mainApi;
    public MainApisImpl(){
        mainApi= RetrofitServiceManager.getInstance().creat(MainApi.class);
    }
    public Observable<AudioTypeBean> getAudioType(){
        return mainApi.getAudioType();
    }
}
