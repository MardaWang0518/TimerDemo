package com.mardawang.android.mydemo;


import android.support.annotation.NonNull;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Administrator on 2016/9/14.
 */
//网路的工具类
public class NetUtil {

    //get请求
    public static void doGet(String url, @NonNull TreeMap<String, String> params, StringCallback callback) {
        doGet(url, new TreeMap<String, String>(), params, callback);
    }




    //get请求
    public static void doGet(String url, @NonNull TreeMap<String, String> headers, @NonNull TreeMap<String, String> params, StringCallback callback) {
        try {
            initTreeMap(headers);
            initTreeMap(params);
            OkHttpUtils.get().url(url).headers(headers).params(params).build().execute(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //post请求
    public static void doPost(String url, @NonNull TreeMap<String, String> params, StringCallback callback) {
        doPost(url, new TreeMap<String, String>(), params, callback);
    }

    //post请求  带header
    public static void doPost(String url, @NonNull TreeMap<String, String> headers, @NonNull TreeMap<String, String> params, StringCallback callback) {
        try {
            initTreeMap(headers);
            initTreeMap(params);
            OkHttpUtils.
                    post().
                    url(url).
                    headers(headers).
                    params(params).
                    build().
                    execute(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initTreeMap(TreeMap<String, String> map) {
        if (map.size() > 0) {
            Set<String> strings = map.keySet();
            for (String key : strings) {
                String value = map.get(key);
                if (null == value) {
                    map.put(key, "");
                }
            }
        }
    }


    //上传图片
    public static void doPostImage(String fileName, File file, StringCallback callback) {
        try {
            OkHttpUtils.
                    post().
                    url("url").
                    addFile("file", fileName, file).
                    build().
                    execute(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //上传文件
    public static void doPostFile(String url, String fileName, File file, StringCallback callback) {
        try {
            OkHttpUtils.
                    post().
                    url(url).
                    addFile("file", fileName, file).
                    addParams("sessionID", "").
                    build().
                    execute(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
