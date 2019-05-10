package com.example.musicplayer.activities;

import android.content.Intent;
import android.os.Handler;

import com.example.musicplayer.R;
import com.example.musicplayer.base.BaseActivity;
import com.example.musicplayer.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    @Override
    protected void initAct() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
