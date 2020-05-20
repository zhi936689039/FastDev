package com.sdacn.yinpin.http.interceptor;

import com.sdacn.yinpin.http.listener.RetrofitDownloadListener;
import com.sdacn.yinpin.http.responsebody.RetrofitResponseBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 下载拦截器
 * Created by Administrator on 2018/6/22.
 */

public class RetrofitDownloadInterceptor implements Interceptor{

    private RetrofitDownloadListener downloadListener;

    public RetrofitDownloadInterceptor(RetrofitDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new RetrofitResponseBody(response.body(), downloadListener)).build();
    }
}
