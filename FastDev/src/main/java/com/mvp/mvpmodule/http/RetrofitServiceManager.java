package com.mvp.mvpmodule.http;


import android.os.Environment;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.mvp.mvpmodule.BuildConfig;
import com.mvp.mvpmodule.http.interceptor.CommonParamsInterceptor;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Administrator
 * @date 2018/3/24
 */

public abstract class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 30;//超时时间
    private static final int DEFAULT_READ_TIME_OUT = 30;//读取时间
    private static final int DEFAULT_WRITE_TIME_OUT = 30;//读取时间
    private static RetrofitServiceManager mRetrofitServiceManager;
    private static Retrofit mRetrofit;

    protected <T> T create(final Class<T> service, String url) {
        if (mRetrofit == null) {
            if (mRetrofit == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
                builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
                builder.writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);
                //设置支持所有https请求
                HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
                builder.hostnameVerifier((hostname, session) -> true).sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
                addInterceptor(builder);
                mRetrofit = new Retrofit.Builder()
                        .client(builder.build())
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
        }
        return mRetrofit.create(service);
    }


    /**
     * 添加各种拦截器
     *
     * @param builder
     */
    private void addInterceptor(OkHttpClient.Builder builder) {
        builder.cache(new Cache(new File(Environment.getExternalStorageDirectory() + "/NetWorkCache"), 20 * 1024 * 1024));
        builder.addInterceptor(new CommonParamsInterceptor());
        for(Interceptor interceptor:getInterceptorList()){
            builder.addInterceptor(interceptor);
        }
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
    }

    protected abstract List<Interceptor> getInterceptorList();
}
