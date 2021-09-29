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
                if (pn.equals(packName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
