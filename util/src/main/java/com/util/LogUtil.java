package com.util;

import android.util.Log;



/**
 * Created by intexh on 2016/8/30.
 */
public class LogUtil {
    private final static boolean IS_DEBUG = BuildConfig.IS_DEBUG; // 是否打印日志

    public static void i(String tag, String msg){
        if(IS_DEBUG){
            Log.i(tag,msg);
        }
    }
    public static void d(String tag, String msg){
        if(IS_DEBUG){
            Log.d(tag,msg);
        }
    }
    public static void w(String tag, String msg){
        if(IS_DEBUG){
            Log.w(tag,msg);
        }
    }
    public static void e(String tag, String msg){
        if(IS_DEBUG){
            Log.e(tag,msg);
        }
    }

    // 使用Log来显示调试信息,因为log在实现上每个message有4k字符长度限制
    // 所以这里使用自己分节的方式来输出足够长度的message
    public static void show(String tag, String message) {
            int strLength = message.length();
            int start = 0;
            int end = 2000;
            if(IS_DEBUG){
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
