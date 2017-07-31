package com.mardawang.android.mydemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mardawang on 2017/7/16.
 */

public class PreferencesUtils {

    private static SharedPreferences sp;
    private static  Context mContext = App.getContext();//
    private static SharedPreferences getPreferences(Context context) {

        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);

        }
        return sp;
    }
    public  static void clearData() {
        SharedPreferences preferences = getPreferences(mContext);
        preferences.edit().clear().commit();
    }

    /**
     * 获得boolean类型的信息,如果没有返回false
     *
     * @param
     * @param key
     * @return
     */
    public static boolean getBoolean( String key) {
        return getBoolean( key, false);
    }

    /**
     * 获得boolean类型的信息
     * @param key
     * @param defValue
     *            ： 没有时的默认值
     * @return
     */
    public static boolean getBoolean(String key,
                                     boolean defValue) {
        SharedPreferences sp = getPreferences(mContext);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 设置boolean类型的 配置数据
     * @param key
     * @param value
     */
    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = getPreferences(mContext);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 获得string类型的信息,如果没有返回null
     *
     * @param key
     * @return
     */
    public static String getString( String key) {
        return getString( key, null);
    }

    /**
     * 获得String类型的信息
     * @param key
     * @param defValue
     *            ： 没有时的默认值
     * @return
     */
    public static String getString( String key, String defValue) {
        SharedPreferences sp = getPreferences(mContext);
        return sp.getString(key, defValue);
    }

    /**
     * 设置String类型的 配置数据
     *
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {
        SharedPreferences sp = getPreferences(mContext);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static int getInt( String key) {
        return getInt( key, 0);
    }

    public static int getInt( String key, int defValue) {
        SharedPreferences sp = getPreferences(mContext);
        return sp.getInt(key, defValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sp = getPreferences(mContext);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }
}
