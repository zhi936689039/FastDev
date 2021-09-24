package com.sdacn.yinpin.module.main.present;
import com.mvp.mvpmodule.base.BasePresent;
import com.mvp.mvpmodule.base.IBaseView;
import com.mvp.mvpmodule.http.ApiCallback;
import com.mvp.mvpmodule.util.LogUtil;
import com.sdacn.yinpin.bean.home.AudioTypeBean;
import com.sdacn.yinpin.module.main.api.MainApisImpl;

/**
 * @author 创建人 ：ouyangSuperHandsome
 * @version 1.0
 * @package 包名 ：com.sdacn.yinpin.module.main.present
 * @createTime 创建时间 ：2020/5/20 21:12
 * @modifyBy 修改人 ：ouyangSuperHandsome
 * @modifyTime 修改时间 ：2020/5/20 21:12
 * @modifyMemo 修改备注：
 */
public class MainPresent extends BasePresent{
    public static final int AUDIO_TYPE=0x001;
    public void getAudioType(){
        addSubscription(new MainApisImpl().getAudioType(),AUDIO_TYPE);

    }

    @Override
    protected ApiCallback getApiCallBack(int type) {
        return null;
    }

    @Override
    protected void onSuccessRequest(int type, Object model) {
        if(isViewAttach()){
            getView().hideLoading();
            LogUtil.e("测试","返回的数据:"+model);
            if(isViewAttach()){
                switch (type){
                    case AUDIO_TYPE:
                        AudioTypeBean audioTypeBean= (AudioTypeBean) model;
                        getView().showResult(audioTypeBean,type);
                        break;
                }
            }
        }

    }

    @Override
    protected void onFail(String msg) {

    }

    @Override
    protected void onRequestComplete() {

    }

    @Override
    protected void loginRemote() {

    }

    @Override
    protected void onFail(String msg, int type) {

    }

}
