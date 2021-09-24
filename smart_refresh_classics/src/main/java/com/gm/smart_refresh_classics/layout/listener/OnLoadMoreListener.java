package com.gm.smart_refresh_classics.layout.listener;


import androidx.annotation.NonNull;

import com.gm.smart_refresh_classics.layout.api.RefreshLayout;

/**
 * 加载更多监听器
 * Created by scwang on 2017/5/26.
 */
public interface OnLoadMoreListener {
    void onLoadMore(@NonNull RefreshLayout refreshLayout);
}
