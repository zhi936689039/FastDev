package com.mvp.mvpmodule.util;

import android.util.Log;

import com.mvp.mvpmodule.BuildConfig;


/**
 * Created by intexh on 2016/8/30.
 */
public class LogUtil {
    public static void i(String tag, String msg,boolean isDebug){
        if(isDebug){
            Log.i(tag,msg);
        }
    }
    public static void d(String tag, String msg,boolean isDebug){
        if(isDebug){
            Log.d(tag,msg);
        }
    }
    public static void w(String tag, String msg,boolean isDebug){
        if(isDebug){
            Log.w(tag,msg);
        }
    }
    public static void e(String tag, String msg,boolean isDebug){
        if(isDebug){
            Log.e(tag,msg);
        }
    }

    // 使用Log来显示调试信息,因为log在实现上每个message有4k字符长度限制
    // 所以这里使用自己分节的方式来输出足够长度的message
    public static void show(String tag, String message,boolean isDebug) {
            int strLength = message.length();
            int start = 0;
            int end = 2000;
            if(isDebug){
                for (int i = 0; i < 100; i++) {
                    if (strLength > end) {
                        Log.w(tag + "___" + i, message.substring(start, end));
                        start = end;
                        end = end + 2000;
                    } else {
                        Log.w(tag + "___" + i, message.substring(start, strLength));
                        break;
                    }
                }
            }

    }

}
