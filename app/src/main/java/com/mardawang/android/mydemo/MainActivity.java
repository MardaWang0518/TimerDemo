package com.mardawang.android.mydemo;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addActivity();
        startService(new Intent(MainActivity.this,TimerService.class));

    }

}
