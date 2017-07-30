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

    public boolean isLoggedIn()
    {
        return settings.getBoolean(Constants.SETTINGS_LOGGED_IN, false);
    }

    public void setLoggedIn()
    {
        editor.putBoolean(Constants.SETTINGS_LOGGED_IN, true).commit();
    }

    public void clearLoggedIn()
    {
        editor.putBoolean(Constants.SETTINGS_LOGGED_IN, false).commit();
    }

    public void setAuthorizationToken(String authorizationToken)
    {
        editor.putString(Constants.SETTINGS_BEARER_TOKEN, authorizationToken).commit();
    }

    public String getAuthroizationToken()
    {
        return settings.getString(Constants.SETTINGS_BEARER_TOKEN, "");
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
