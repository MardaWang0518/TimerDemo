package com.mardawang.android.mydemo;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class WebviewActivity extends BaseActivity {

    private String local_url;
    private int local_status;

    private WebView mWebview;
    private boolean mIsExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        addActivity();
        mWebview = (WebView) findViewById(R.id.webview);

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //Sets whether the WebView whether supports multiple windows.
        //If set to true, WebChromeClient.onCreateWindow must be implemented by the host application.
        //The default is false.
        mWebview.getSettings().setSupportMultipleWindows(false); // 不要轻易设置为true，否则有些链接不能点击跳转
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setSupportZoom(false);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setSavePassword(false);

        mWebview.getSettings().setDomStorageEnabled(false);
        mWebview.getSettings().setAppCacheEnabled(false);
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        if (local_status != getIntent().getIntExtra("status", 0)) {
            local_url = getIntent().getStringExtra("url");
            local_status = getIntent().getIntExtra("status", 0);
            mWebview.loadUrl(local_url);
        } else if (local_url != getIntent().getStringExtra("url")) {
            local_url = getIntent().getStringExtra("url");
            mWebview.loadUrl(local_url);

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                removeALLActivity();
                PreferencesUtils.putBoolean("load_again",true);
            } else {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
