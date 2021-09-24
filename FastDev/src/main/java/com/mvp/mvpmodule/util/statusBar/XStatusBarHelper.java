package com.mvp.mvpmodule.util.statusBar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.ColorInt;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;


/**
 * 状态栏工具类
 *
 * @author shihao
 */
public class XStatusBarHelper {

    /**
     * Android4.4以上的沉浸式全屏模式
     * @param activity Activity对象（android 6.0以上的随便玩，6.0到5.0的如果布局整体颜色不是白色的话，可以设置这个，否则用系统默认那个）
     */
    public static void immersiveStatusBar(Activity activity) {
        //调用BarUtils.setStatusBarColor之后，布局将会顶上状态栏
        BarUtils.setStatusBarColor(activity,Color.TRANSPARENT);
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        return BarUtils.getStatusBarHeight();
    }

    /**
     * Android4.4以上的状态栏着色
     *
     * @param activity       Activity对象
     * @param statusBarColor 状态栏颜色
     */
    public static void tintStatusBar(Activity activity, @ColorInt int statusBarColor) {
        //调用BarUtils.setStatusBarColor之后，布局将会顶上状态栏
        BarUtils.setStatusBarColor(activity,statusBarColor);
    }


    /**
     * Android4.4以上的状态栏着色(针对于DrawerLayout)
     *
     * @param drawerLayout   DrawerLayout对象
     * @param statusBarColor 状态栏颜色
     * @param fakeStatusBar 状态栏假view
     */
    public static void tintStatusBarForDrawer(DrawerLayout drawerLayout, View fakeStatusBar, @ColorInt int statusBarColor) {
        //调用BarUtils.setStatusBarColor4Drawer，布局将会顶上状态栏
        BarUtils.setStatusBarColor4Drawer(drawerLayout,fakeStatusBar,statusBarColor);
    }

    /**
     * 增加View的marginTop,值为状态栏的高度
     *
     * @param context
     * @param view    一般沉浸式全屏模式下用于Toolbar或者自定义的标题栏
     */
    public static void addMargin(Context context, View view) {
        BarUtils.addMarginTopEqualStatusBarHeight(view);
    }

    /**
     * 设置view的高度，最终高度为view加上状态栏高度
     *
     * @param context
     * @param view    一般沉浸式全屏模式下用于Toolbar或者自定义的标题栏
     */
    public static void setHeight(Context context, View view) {
        BarUtils.setStatusBarCustom(view);
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变黑(目前支持MIUI6以上,Flyme4以上,Android M以上)
     */
    public static void setStatusBarDarkMode(Activity activity,boolean isDarkMode) {
        BarUtils.setStatusBarLightMode(activity,isDarkMode);
    }

    /**
     * 设置状态栏为darkMode,字体颜色及icon变黑(目前支持MIUI6以上,Flyme4以上,Android M以上)
     */
    public static void setStatusBarDarkMode(Window window,boolean isDarkMode) {
        BarUtils.setStatusBarLightMode(window,isDarkMode);
    }

    /**
     * 强制将Activity绑定的xml下面的子View的FitsSystemWindows为false
     */
    public static void forceFitsSystemWindows(Activity activity) {
        forceFitsSystemWindows(activity.getWindow());
    }

    /**
     * 强制将Window绑定的xml下面的子View的FitsSystemWindows为false
     *
     * @param window
     */
    public static void forceFitsSystemWindows(Window window) {
        forceFitsSystemWindows((ViewGroup) window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT));
    }

    /**
     * 强制将ViewGroup下面的子View的FitsSystemWindows为false
     *
     * @param viewGroup
     */
    public static void forceFitsSystemWindows(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof ViewGroup) {
                    forceFitsSystemWindows((ViewGroup) view);
                } else {
                    if (ViewCompat.getFitsSystemWindows(view)) {
                        ViewCompat.setFitsSystemWindows(view, false);
                    }
                }
            }
        }
    }
    //全屏，隐藏状态栏
    public static void hideVirtualButton(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            // use reflection to remove dependence of API level
            Class viewClass = View.class;
            final int SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = RexflectionHelper.<Integer>getConstantValue(viewClass, "SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION");
            final int SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN = RexflectionHelper.<Integer>getConstantValue(viewClass, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN");
            final int SYSTEM_UI_FLAG_HIDE_NAVIGATION = RexflectionHelper.<Integer>getConstantValue(viewClass, "SYSTEM_UI_FLAG_HIDE_NAVIGATION");
            final int SYSTEM_UI_FLAG_FULLSCREEN = RexflectionHelper.<Integer>getConstantValue(viewClass, "SYSTEM_UI_FLAG_FULLSCREEN");
            final int SYSTEM_UI_FLAG_IMMERSIVE_STICKY = RexflectionHelper.<Integer>getConstantValue(viewClass, "SYSTEM_UI_FLAG_IMMERSIVE_STICKY");
            final int SYSTEM_UI_FLAG_LAYOUT_STABLE = RexflectionHelper.<Integer>getConstantValue(viewClass, "SYSTEM_UI_FLAG_LAYOUT_STABLE");

            Object[] parameters = null;
            // getWindow().getDecorView().setSystemUiVisibility();
            parameters = new Object[]{SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | SYSTEM_UI_FLAG_IMMERSIVE_STICKY};
            RexflectionHelper.<Void>invokeInstanceMethod(activity.getWindow().getDecorView(),
                    "setSystemUiVisibility",
                    new Class[]{Integer.TYPE},
                    parameters);
        }
    }
}