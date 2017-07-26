package com.litedoid.orachat;

import android.app.Application;

import org.androidannotations.annotations.EApplication;

@EApplication
public class OraChatApplication extends Application
{
    private static final String TAG = OraChatApplication.class.getSimpleName();

    @Override
    public void onCreate()
    {
        super.onCreate();

    }

}
