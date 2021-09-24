package com.gm.smart_refresh_classics.layout.listener;


import androidx.annotation.NonNull;

import com.gm.smart_refresh_classics.layout.api.RefreshLayout;

/**
 * 刷新监听器
 * Created by scwang on 2017/5/26.
 */
public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
