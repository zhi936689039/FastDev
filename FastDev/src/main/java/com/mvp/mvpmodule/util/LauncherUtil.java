package com.mvp.mvpmodule.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.List;

/**
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：com.renrenda.client.util
 * @createTime 创建时间 ：2019/9/23
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/9/23
 * @modifyMemo 修改备注：
 */
public class LauncherUtil {
    public static boolean isExitApp(Context context,String packName){
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void launcherApp(Context context,String packageName){
        //包管理器对象
        PackageManager pm = context.getPackageManager();
        //检查包名
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName,0);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        //包名存在则启动app
        if (packageInfo != null){
            Intent intent = pm.getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"应用程序未安装",Toast.LENGTH_LONG).show();
        }
    }
}
