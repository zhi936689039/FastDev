package com.gm.smart_refresh_classics.layout.listener;

import android.content.Context;

import androidx.annotation.NonNull;

import com.gm.smart_refresh_classics.layout.api.RefreshHeader;
import com.gm.smart_refresh_classics.layout.api.RefreshLayout;


/**
 * 默认Header创建器
 * Created by scwang on 2018/1/26.
 */
public interface DefaultRefreshHeaderCreator {
    @NonNull
    RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout);
}
