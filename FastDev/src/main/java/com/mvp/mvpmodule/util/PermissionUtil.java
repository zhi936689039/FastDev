package com.mvp.mvpmodule.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.mvp.mvpmodule.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

    /**
     * Build.MANUFACTURER判断各大手机厂商品牌
     */
    private static final String MANUFACTURER_HUAWEI = "Huawei";//华为
    private static final String MANUFACTURER_MEIZU = "Meizu";//魅族
    private static final String MANUFACTURER_XIAOMI = "Xiaomi";//小米
    private static final String MANUFACTURER_SONY = "Sony";//索尼
    private static final String MANUFACTURER_OPPO = "OPPO";
    private static final String MANUFACTURER_LG = "LG";
    private static final String MANUFACTURER_VIVO = "vivo";
    private static final String MANUFACTURER_SAMSUNG = "samsung";//三星
    private static final String MANUFACTURER_LETV = "Letv";//乐视
    private static final String MANUFACTURER_ZTE = "ZTE";//中兴
    private static final String MANUFACTURER_YULONG = "YuLong";//酷派
    private static final String MANUFACTURER_LENOVO = "LENOVO";//联想
    public static final int RC_PERMISSIONS = 100;
    public static boolean isAppSettingOpen=false;

    public static boolean hasPermissions(Context context, String[] permissions) {
        int count = 0;

        for (String permission : permissions) {
            if (hasPermission(context, permission)) {
                count++;
            }
        }

        return permissions.length == count;
    }

    public static boolean hasPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(activity,permissions, requestCode);
        }
    }

    public static void requestPermissions(Fragment fragment, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragment.requestPermissions(permissions, requestCode);
        }
    }

    public static boolean verifyPermissions(int... grantResults) {
        if (grantResults.length == 0) {
            return false;
        }

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static String[] getAllowedPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> allowedPermissions = new ArrayList<>();
            for (String permission : permissions) {
                if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                    allowedPermissions.add(permission);
                }
            }

            if (!allowedPermissions.isEmpty()) {
                return allowedPermissions.toArray(new String[allowedPermissions.size()]);
            }
        }
        return null;
    }

    public static String[] getDeniedPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> deniedPermissions = new ArrayList<>();
            for (String permission : permissions) {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permission);
                }
            }
            if (!deniedPermissions.isEmpty()) {
                return deniedPermissions.toArray(new String[deniedPermissions.size()]);
            }
        }
        return null;
    }

    public static Dialog showWaringDialog(Activity context) {
        return DialogUtil.showPermissionWarningDialog(context, new DialogUtil.DialogImpl() {
            @Override
            public void onOk() {
                goToSetting(context);
            }

            @Override
            public void onCancle() {

            }
        });
    }
    /**
     * 跳转到相应品牌手机系统权限设置页，如果跳转不成功，则跳转到应用详情页
     * 这里需要改造成返回true或者false，应用详情页:true，应用权限页:false
     * @param activity
     */
    public static void goToSetting(Activity activity){
        switch (Build.MANUFACTURER){
            case MANUFACTURER_HUAWEI://华为
                Huawei(activity);
                break;
            case MANUFACTURER_MEIZU://魅族
                Meizu(activity);
                break;
            case MANUFACTURER_XIAOMI://小米
                Xiaomi(activity);
                break;
            case MANUFACTURER_SONY://索尼
                Sony(activity);
                break;
            case MANUFACTURER_OPPO://oppo
                OPPO(activity);
                break;
            case MANUFACTURER_LG://lg
                LG(activity);
                break;
            case MANUFACTURER_LETV://乐视
                Letv(activity);
                break;
            default://其他
                try {//防止应用详情页也找不到，捕获异常后跳转到设置，这里跳转最好是两级，太多用户也会觉得麻烦，还不如不跳
                    openAppDetailSetting(activity);
//                    activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
                }catch (Exception e){
                    SystemConfig(activity);
                }
                break;
        }
    }

    /**
     * 华为跳转权限设置页
     * @param activity
     */
    public static void Huawei(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
            intent.setComponent(comp);
            activity.startActivityForResult(intent,RC_PERMISSIONS);
            isAppSettingOpen=false;
        } catch (Exception e) {
            openAppDetailSetting(activity);
//            activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
        }
    }

    /**
     * 魅族跳转权限设置页，测试时，点击无反应，具体原因不明
     * @param activity
     */
    public static void Meizu(Activity activity) {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            activity.startActivityForResult(intent,RC_PERMISSIONS);
            isAppSettingOpen=false;
        }catch (Exception e){
            openAppDetailSetting(activity);
//            activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
        }
    }

    /**
     * 小米，功能正常
     * @param activity
     */
    public static void Xiaomi(Activity activity) {
        try { // MIUI 8 9
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", activity.getPackageName());
            activity.startActivityForResult(localIntent,RC_PERMISSIONS);
            isAppSettingOpen=false;
//            activity.startActivity(localIntent);
        } catch (Exception e) {
            try { // MIUI 5/6/7
                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                localIntent.putExtra("extra_pkgname", activity.getPackageName());
                activity.startActivityForResult(localIntent,RC_PERMISSIONS);
                isAppSettingOpen=false;
//                activity.startActivity(localIntent);
            } catch (Exception e1) { // 否则跳转到应用详情
                openAppDetailSetting(activity);
//                activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
                //这里有个问题，进入活动后需要再跳一级活动，就检测不到返回结果
//                activity.startActivity(getAppDetailSettingIntent());
            }
        }
    }

    /**
     * 索尼，6.0以上的手机非常少，基本没看见
     * @param activity
     */
    public static void Sony(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity");
            intent.setComponent(comp);
            activity.startActivityForResult(intent,RC_PERMISSIONS);
            isAppSettingOpen=false;
        }catch (Exception e){
            openAppDetailSetting(activity);
//            activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
        }
    }

    /**
     * OPPO
     * @param activity
     */
    public static void OPPO(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
            intent.setComponent(comp);
            activity.startActivityForResult(intent,RC_PERMISSIONS);
            isAppSettingOpen=false;
        }catch (Exception e){
            openAppDetailSetting(activity);
//            activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
        }
    }

    /**
     * LG经过测试，正常使用
     * @param activity
     */
    public static void LG(Activity activity) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
            intent.setComponent(comp);
            activity.startActivityForResult(intent,RC_PERMISSIONS);
            isAppSettingOpen=false;
        }catch (Exception e){
            openAppDetailSetting(activity);
//            activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
        }
    }

    /**
     * 乐视6.0以上很少，基本都可以忽略了，现在乐视手机不多
     * @param activity
     */
    public static void Letv(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps");
            intent.setComponent(comp);
            activity.startActivityForResult(intent,RC_PERMISSIONS);
            isAppSettingOpen=false;
        }catch (Exception e){
            openAppDetailSetting(activity);
//            activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
        }
    }

    /**
     * 只能打开到自带安全软件
     * @param activity
     */
    public static void _360(Activity activity) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            ComponentName comp = new ComponentName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
            intent.setComponent(comp);
            activity.startActivity(intent);
        }catch (Exception e){
            openAppDetailSetting(activity);
//            activity.startActivityForResult(getAppDetailSettingIntent(activity), PERMISSION_SETTING_FOR_RESULT);
        }
    }
    /**
     * 系统设置界面
     * @param activity
     */
    public static void SystemConfig(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        activity.startActivity(intent);
    }
    /**
     * 获取应用详情页面
     * @return
     */
    private static Intent getAppDetailSettingIntent(Activity activity) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        }
        return localIntent;
    }
    public static void openAppDetailSetting(Activity activity){
        activity.startActivityForResult(getAppDetailSettingIntent(activity), RC_PERMISSIONS);
        isAppSettingOpen=true;
    }
}