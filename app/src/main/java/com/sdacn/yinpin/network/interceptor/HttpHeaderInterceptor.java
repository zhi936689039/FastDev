package com.sdacn.yinpin.network.interceptor;

import com.mvp.mvpmodule.BuildConfig;
import com.mvp.mvpmodule.util.LogUtil;
import com.mvp.mvpmodule.util.SettingsUtil;
import com.mvp.mvpmodule.util.ValidateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/24.
 */

public class HttpHeaderInterceptor implements Interceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();
    private String affsId="AppsFlyerId";

    public HttpHeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        // 新的请求
        Request.Builder requestBuilder = oldRequest.newBuilder();
        LogUtil.e("网络请求","当前请求url:"+oldRequest.url(), BuildConfig.DEBUG);
//        if(UserHelper.getInstance().isLogin()){
//            requestBuilder.addHeader("Authorization", UserHelper.getInstance().getCurrentUserToken());
//            requestBuilder.addHeader("channel", ChannelUtil.getMainChannel());
//        }
//        if(!ValidateUtils.isValidate(SettingsUtil.getString(affsId,""))){
//            SettingsUtil.setString(affsId, AppsFlyerLib.getInstance().getAppsFlyerUID(App.getInstance().getApplicationContext()));
//        }
        requestBuilder.method(oldRequest.method(),
                oldRequest.body());
        Request newRequest = requestBuilder.build();

        LogUtil.e("网络请求","当前请求头:"+newRequest.headers().toString(), BuildConfig.DEBUG);
        return chain.proceed(newRequest);
    }

    public static class Builder {
        HttpHeaderInterceptor mHttpHeaderInterceptor;

        public Builder() {
            mHttpHeaderInterceptor = new HttpHeaderInterceptor();
        }
        public HttpHeaderInterceptor build() {
            return mHttpHeaderInterceptor;
        }
    }

}
