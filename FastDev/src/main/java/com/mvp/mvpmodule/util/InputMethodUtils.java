package com.mvp.mvpmodule.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：com.xdp.client.util
 * @createTime 创建时间 ：2019/9/17
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/9/17
 * @modifyMemo 修改备注：
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class InputMethodUtils {

    public static void toggleSoftInput(Context context) {
    	if(context == null) return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean showSoftInput(View view) {
    	if(view == null) return false;
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static boolean showSoftInput(Activity activity) {
    	if(activity == null) return false; 
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
        return false;
    }

    public static boolean hideSoftInput(View view) {
    	if(view == null) return false;
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean hideSoftInput(Activity activity) {
    	if(activity == null) return false; 
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            return imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
        return false;
    }

    public static boolean isActive(Context context) {
    	if(context == null) return false;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }


}
