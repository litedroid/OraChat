package com.litedoid.orachat;

import android.content.Context;
import android.content.SharedPreferences;

public class ApplicationSettings
{
    private static final String TAG = ApplicationSettings.class.getSimpleName();

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    private static ApplicationSettings applicationSettings;

    public ApplicationSettings(Context context)
    {
        settings = context.getSharedPreferences(Constants.FILE_APPSETTINGS, 0);
        editor = settings.edit();
    }

    public static ApplicationSettings sharedSettings()
    {
        if (applicationSettings == null)
            applicationSettings = new ApplicationSettings(OraChatApplication_.getInstance().getApplicationContext());

        return applicationSettings;
    }

    public void clearSession()
    {
        editor.clear().apply();

        settings = null;
        editor = null;

        applicationSettings = new ApplicationSettings(OraChatApplication_.getInstance().getApplicationContext());
    }

}
