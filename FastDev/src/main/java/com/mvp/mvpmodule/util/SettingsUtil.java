package com.mvp.mvpmodule.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import java.util.ArrayList;


/**
 * @author: Milk <249828165@qq.com>
 * @since: 13-9-11
 * 设置管理类
 */
public class SettingsUtil
{
    private static Context mContext;
    private static SharedPreferences mPrefs;
    public static final String KEYBOARD_HEIGHT = "keyboard_height";
    public static final String SHOWING_WINDOW_HEIGHT = "showing_window_height";
    public static final String TAB_TITLE="title";
    public static final String CHAT_MESSAGE_DO_NOT_DISTURB="chat_message_do_not_disturb";
    public static final String CHAT_VIBRATOR="chat_vibrator";
    public static final String CHAT_VOICE="chat_voice";


    // 一些比较通用的值 --------
    /*主题*/
    public static String theme = "light";
    /* 重新发送验证码间隔：秒 */
    public static int ReSendCodeInterval = 30;
    // 结束 -------------------
   /*开发模式*/
    public final static boolean DEBUG = true;

    private static ArrayList<OnSharedPreferenceChangeListener> mListeners = new ArrayList<>();

    public static void initialize(final Context context)
    {
        mContext = context.getApplicationContext();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        mPrefs.registerOnSharedPreferenceChangeListener(mOnSharedPreferenceChangeListener);
        onPreferenceChanged(mPrefs, null);
    }

    //fix 4.29： bug2 
    private static SharedPreferences getSharedPreferences(){
    	if (mPrefs==null) {
            mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
		}
    	return mPrefs;
    }

    public static SharedPreferences getPrivatePreferences(String name)
    {
        return mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void addOnSharedPreferenceChanged(OnSharedPreferenceChangeListener l)
    {
        mListeners.add(l);
    }

    static OnSharedPreferenceChangeListener mOnSharedPreferenceChangeListener = new OnSharedPreferenceChangeListener()
    {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
        {
            onPreferenceChanged(sharedPreferences, key); //先要保证本类是最新的，才能让其它类用
            int size = mListeners.size();
            for (int i=0; i<size; i++)
            {
                mListeners.get(i).onSharedPreferenceChanged(sharedPreferences, key);
            }
        }
    };

    private static void onPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        //重新读取配置到变量属性
        if (key == null || "theme".equals(key))
            theme = getSharedPreferences().getString("theme", theme);

    }

    public static void cleanAll()
    {
    	getSharedPreferences().edit().clear().commit();
    }

    public static String getVersion()
    {
        return getSharedPreferences().getString("version", "-1");
    }

    public static String getString(String key, String defaultValue)
    {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public static boolean setString(String key, String value)
    {
        boolean result = getSharedPreferences().edit().putString(key, value).commit();
        if(result)
            onPreferenceChanged(getSharedPreferences(), key);
        return result;
    }
    public static boolean getBoolean(String key, boolean defaultValue)
    {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public static boolean setBoolean(String key, boolean value)
    {
        boolean result = getSharedPreferences().edit().putBoolean(key, value).commit();
        if(result)
            onPreferenceChanged(getSharedPreferences(), key);
        return result;
    }

    public static long getLong(String key, long defaultValue)
    {
            return getSharedPreferences().getLong(key, defaultValue);
    }

    public static boolean setLong(String key, long value)
    {
        boolean result = getSharedPreferences().edit().putLong(key, value).commit();
        if(result)
            onPreferenceChanged(getSharedPreferences(), key);
        return result;
    }

    public static int getInt(String key, int defaultValue)
    {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public static boolean setInt(String key, int value)
    {
        boolean result = getSharedPreferences().edit().putInt(key, value).commit();
        if(result)
            onPreferenceChanged(getSharedPreferences(), key);
        return result;
    }
    

    public static double getDouble(String key, double defaultValue)
    {
        return Double.valueOf(getString(key, String.valueOf(defaultValue)));
    }

    public static boolean setDouble(String key, double value)
    {
        return setString(key, String.valueOf(value));
    }

    public static boolean remove(String key)
    {
        return getSharedPreferences().edit().remove(key).commit();
    }
}
