package com.gm.smart_refresh_classics.layout.wrapper;

import android.annotation.SuppressLint;
import android.view.View;

import com.gm.smart_refresh_classics.layout.api.RefreshHeader;
import com.gm.smart_refresh_classics.layout.simple.SimpleComponent;


/**
 * 刷新头部包装
 * Created by scwang on 2017/5/26.
 */
@SuppressLint("ViewConstructor")
public class RefreshHeaderWrapper extends SimpleComponent implements RefreshHeader {

    public RefreshHeaderWrapper(View wrapper) {
        super(wrapper);
    }

}
