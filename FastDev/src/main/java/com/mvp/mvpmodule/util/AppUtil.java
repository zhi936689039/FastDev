package com.mvp.mvpmodule.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.core.app.NotificationManagerCompat;

import java.util.List;

/**
 * App 工具
 *
 * @author fence
 * @date 2019-09-10
 */
public class AppUtil {

    /**
     * 获取App应用图标
     *
     * @param context
     * @return
     */
    public static Drawable getIcon(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(getPackage(context), 0);
            return info.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取App名称
     *
     * @param context
     * @return
     */
    public static String getName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo(getPackage(context), 0);
            return info.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return "";
    }

    /**
     * 获取App包名
     *
     * @param context
     * @return
     */
    public static String getPackage(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid) {
                return info.processName;
            }
        }
        return "";
    }

    /**
     * 获取 versionCode
     *
     * @param context 上下文
     * @return versionCode
     */
    public static int getVersionCode(Context context) {
        if (context == null) {
            return 0;
        }

        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 获取 versionName
     *
     * @param context 上下文
     * @return versionName
     */
    public static String getVersionName(Context context) {
        if (context == null) {
            return null;
        }

        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getIntVersionName(Context context) {
        String versionName = getVersionName(context);
        versionName = versionName.replace(".", "");
        return Integer.parseInt(versionName);
    }

    /**
     * 获取元数据
     *
     * @param context
     * @param meta
     * @return
     */
    public static String getMeta(Context context, String meta) {
        ApplicationInfo appInfo = null;

        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (appInfo.metaData == null) {
            return "";
        } else {
            return appInfo.metaData.getString(meta);
        }
    }

    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        String IMEI = "";

        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            IMEI = telephonyManager.getDeviceId();
        } catch (Exception e) {
        }

        if (TextUtils.isEmpty(IMEI) || IMEI.equals("000000000000000")) {
            IMEI = getAndroidId(context);
        }

        return TextUtils.isEmpty(IMEI) ? "" : IMEI;
    }

    /**
     * 获取设备ID
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        String androidId = "";
        androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取操作系统版本号
     *
     * @return
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }
    public static String getAndroidVersion(){
        return Build.VERSION.SDK;
    }

    /**
     * 通知设置是否开启
     *
     * @param context
     * @return
     */
    public static boolean isNotificationEnabled(Context context) {
        return NotificationManagerCompat.from(context.getApplicationContext()).areNotificationsEnabled();
    }

    /**
     * 打开通知设置页面
     *
     * @param context
     */
    public static void openNotificationSetting(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, context.getApplicationInfo().uid);
            context.startActivity(intent);
        } else {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                jumpToSystemConfig(context);
            } else {
                try {
                    jumpToApplicationInfo(context);
                } catch (Exception e) {
                    e.printStackTrace();
                    jumpToSystemConfig(context);
                }
            }
        }
    }

    /**
     * 应用信息界面
     *
     * @param context
     */
    public static void jumpToApplicationInfo(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(localIntent);
    }

    /**
     * 系统设置界面
     *
     * @param context
     */
    public static void jumpToSystemConfig(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据包名判断是否安装app
     *
     * @param context 上下文
     * @param pkgName 包名字符串
     * @return 是否安装布尔值
     */
    public static boolean checkAppInstalled(Context context, String pkgName) {
        boolean isInstalled = false;
        if (pkgName == null || pkgName.isEmpty()) {
            isInstalled = false;
        }

        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            isInstalled = true;
        }
        return isInstalled;
    }

}
