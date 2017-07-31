package com.mardawang.android.mydemo;

import com.google.gson.Gson;

/**
 * Created by hxh on 2017/5/18.
 */
public class GsonUtil {
    private static Gson gson;
    public static Gson defaultGson()
    {
        if(null == gson)
        {
            gson = new Gson();
        }
        return gson;
    }
}
