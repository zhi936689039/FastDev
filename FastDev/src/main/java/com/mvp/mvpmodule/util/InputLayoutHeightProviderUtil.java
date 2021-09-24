package com.mvp.mvpmodule.util;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;

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
 * @package 包名 ：com.util
 * @createTime 创建时间 ：2019/12/27
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/12/27
 * @modifyMemo 修改备注：
 */
public class InputLayoutHeightProviderUtil  extends PopupWindow implements ViewTreeObserver.OnGlobalLayoutListener {
    private Activity mActivity;
    private View rootView;
    private HeightListener listener;
    private int heightMax; // 记录popup内容区的最大高度

    public InputLayoutHeightProviderUtil(Activity activity) {
        super(activity);
        this.mActivity = activity;

        // 基础配置
        rootView = new View(activity);
        setContentView(rootView);

        // 监听全局Layout变化
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        setBackgroundDrawable(new ColorDrawable(0));

        // 设置宽度为0，高度为全屏
        setWidth(0);
        setHeight(LayoutParams.MATCH_PARENT);

        // 设置键盘弹出方式
        setSoftInputMode(LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
    }

    public InputLayoutHeightProviderUtil init() {
        try{
            if (!isShowing()) {
                final View view = mActivity.getWindow().getDecorView();
                // 延迟加载popupwindow，如果不加延迟就会报错
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
                        }catch (Exception e){

                        }

                    }
                });
            }
        }catch (Exception e){

        }

        return this;
    }

    public InputLayoutHeightProviderUtil setHeightListener(HeightListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        if (rect.bottom > heightMax) {
            heightMax = rect.bottom;
        }

        // 两者的差值就是键盘的高度
        int keyboardHeight = heightMax - rect.bottom;
        if (listener != null) {
            listener.onHeightChanged(keyboardHeight);
        }
    }

    public interface HeightListener {
        void onHeightChanged(int height);
    }
}
