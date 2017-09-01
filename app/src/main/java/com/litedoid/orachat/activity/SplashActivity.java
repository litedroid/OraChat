package com.litedoid.orachat.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.litedoid.orachat.ApplicationSettings;
import com.litedoid.orachat.R;
import com.litedoid.orachat.controller.auth.AuthActivity_;
import com.litedoid.orachat.controller.main.MainActivity_;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity
{
    private static final String TAG = SplashActivity.class.getSimpleName();

    private final static int SPLASH_DURATION = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Log.d(TAG, "onResume");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (ApplicationSettings.sharedSettings().isLoggedIn())
                {
                    MainActivity_.intent(SplashActivity.this).start();
                }
                else
                {
                    AuthActivity_.intent(SplashActivity.this).start();
                }

                finish();
            }
        }, SPLASH_DURATION);

    }

}
