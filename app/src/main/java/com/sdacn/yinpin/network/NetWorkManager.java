package com.sdacn.yinpin.network;

import com.mvp.mvpmodule.http.RetrofitServiceManager;
import com.sdacn.yinpin.network.interceptor.HttpHeaderInterceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * @user oyzb
 * @date 2021/9/23 14:48
 */
public class NetWorkManager extends RetrofitServiceManager {
    private static class SingletonFactory {
        private static NetWorkManager sInstance = new NetWorkManager();
    }

    public static NetWorkManager getInstance() {
        return NetWorkManager.SingletonFactory.sInstance;
    }

    public <T> T netCreate(final Class<T> service) {
        return create(service, "");
    }

    @Override
    protected List<Interceptor> getInterceptorList() {
        List<Interceptor> interceptorList = new ArrayList<>();
        HttpHeaderInterceptor httpHeaderInterceptor = new HttpHeaderInterceptor.Builder().build();
        interceptorList.add(httpHeaderInterceptor);
        return interceptorList;
    }
}
