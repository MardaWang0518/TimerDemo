package com.mardawang.android.mydemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.zhy.http.okhttp.callback.StringCallback;

import java.util.TreeMap;

import okhttp3.Call;

/**
 * Created by mardawang on 2017/7/14.
 */

public class TimerService extends Service {
    //    private String origin_url = "http://www.0crm.cn/InfoCRM-SM/zinterface/getLdStatus.ehtm";
    private String origin_url = "http://www.0crm.cn/zinterface/getLdStatus.ehtm";

    private String url;
    private int status;
    private boolean skipable = true;//是否code从0到1（强制前台进程）
    private boolean updateUrl = false;//url是否发生变化

    private boolean backToForeground = true;//是否从后台切换到前台进程
    boolean isfirst = true;//是否对后台切换到前台做过判断

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getData();
            if (skipable) {
                backToForeground = true;
                loadWeb();
            }

            Log.i("台", "backToForeground:"+backToForeground+"   isfirst:"+isfirst);

            if (App.isForeground() && isfirst && !backToForeground) {
                backToForeground = true;
            }
            if (!App.isForeground()) {
                isfirst = true;
                backToForeground = false;
            }

            if ((!skipable && updateUrl && App.isForeground())) {
                loadWeb();
            } else if (isfirst && backToForeground) {
                isfirst = false;
                loadWeb();
            }
            Log.i("台", "backToForeground:"+backToForeground+"   isfirst:"+isfirst);

        }
    };

    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        getData();

    }

    private void getData() {
        handler.sendEmptyMessageDelayed(status, 3 * 1000);

        NetUtil.doPost(origin_url, new TreeMap<String, String>(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(TimerService.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {

                String res1 = response.substring(1, response.length());
                String res2 = res1.substring(0, res1.length() - 1);

                boolean load_again = PreferencesUtils.getBoolean("load_again", false);
                MyBean bean = GsonUtil.defaultGson().fromJson(res2, MyBean.class);
                if (bean != null) {
                    if (url == null || url.isEmpty() || (status == 0 && "1".equals(bean.getStatus())) || load_again) {
                        skipable = true;
                        if (load_again) {
                            PreferencesUtils.putBoolean("load_again", false);
                        }
                    } else {
                        skipable = false;
                    }

                    if (url != null && !url.equals(bean.getUrl())) {
                        updateUrl = true;
                    } else {
                        updateUrl = false;
                    }

                    url = bean.getUrl();
                    status = Integer.parseInt(bean.getStatus());
                }
            }
        });
    }

    private void loadWeb() {
        Intent intent = new Intent(TimerService.this, WebviewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("url", url);
        intent.putExtra("status", status);
        startActivity(intent);
    }
}
