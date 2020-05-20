package com.sdacn.yinpin.module.main.api;

import com.sdacn.yinpin.bean.home.AudioTypeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author 创建人 ：ouyangSuperHandsome
 * @version 1.0
 * @package 包名 ：com.sdacn.yinpin.module.main.api
 * @createTime 创建时间 ：2020/5/20 22:48
 * @modifyBy 修改人 ：ouyangSuperHandsome
 * @modifyTime 修改时间 ：2020/5/20 22:48
 * @modifyMemo 修改备注：
 */
public interface MainApi {

    //获取首页音频分类
    @GET("Multimedia/audiogrouping")
    Observable<AudioTypeBean> getAudioType();
}
