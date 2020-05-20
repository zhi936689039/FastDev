package com.util.videoCache;

import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

/**
 * 　       ┏┓　　　┏┓
 * # 　　┏┛┻━━━┛┻┓
 * # 　　┃　　　　　　　 ┃
 * # 　　┃　　　━　　　 ┃
 * # 　　┃　┳┛　┗┳　 ┃
 * # 　　┃　　　　　　　 ┃
 * # 　　┃　　　┻　　　 ┃
 * # 　　┃　　　　　　　 ┃
 * # 　　┗━┓　　　┏━┛Codes are far away from bugs with the animal protecting
 * # 　　　　┃　　　┃    神兽保佑,代码无bug
 * # 　　　　┃　　　┃
 * # 　　　　┃　　　┗━━━┓
 * # 　　　　┃　　　　　 ┣┓
 * # 　　　　┃　　　　 ┏┛
 * # 　　　　┗┓┓┏━┳┓┏┛
 * # 　　　　　┃┫┫　┃┫┫
 * # 　　　　　┗┻┛　┗┻┛
 *
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：com.util.videoCache
 * @createTime 创建时间 ：2019/12/20
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/12/20
 * @modifyMemo 修改备注：
 */
public enum  MyHttpProxyCacheServer {

    INSTANCE;
    private HttpProxyCacheServer proxy;

    public  HttpProxyCacheServer getProxy(Context context) {
        return proxy == null ? (proxy = newProxy(context)) :proxy;
    }

    private HttpProxyCacheServer newProxy(Context context) {
        return new HttpProxyCacheServer.Builder(context)
                .maxCacheSize(1024*1024*1024)       // 1G最多
                .fileNameGenerator(new MyFileNameGenerator())
                .build();
    }

}
