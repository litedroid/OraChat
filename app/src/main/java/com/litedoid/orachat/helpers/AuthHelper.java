package com.litedoid.orachat.helpers;


import android.util.Log;

import com.litedoid.orachat.ApplicationSettings;

import retrofit2.Response;

import static com.litedoid.orachat.api.client.OraChatAPIClient.HEADER_AUTHORIZATION;

public class AuthHelper
{
    private static final String TAG = AuthHelper.class.getSimpleName();

    public static void setAuthorizationTokenFromHeaders(Response response)
    {
        Log.d(TAG, response.headers().get(HEADER_AUTHORIZATION));

        if (response.headers().get(HEADER_AUTHORIZATION) != null)
            ApplicationSettings.sharedSettings().setLoggedIn();
    }
}

