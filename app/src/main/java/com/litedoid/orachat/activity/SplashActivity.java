package com.litedoid.orachat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.litedoid.orachat.ApplicationSettings;
import com.litedoid.orachat.R;
import com.litedoid.orachat.controller.auth.AuthActivity_;
import com.litedoid.orachat.controller.main.MainActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity
{
    private static final String TAG = SplashActivity.class.getSimpleName();

    private final static int SPLASH_DURATION = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @UiThread(delay = SPLASH_DURATION)
    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

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

}
