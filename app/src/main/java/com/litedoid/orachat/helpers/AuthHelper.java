package com.litedoid.orachat.helpers;


import android.util.Log;

import com.litedoid.orachat.ApplicationSettings;

import retrofit.client.Header;
import retrofit.client.Response;

import static com.litedoid.orachat.api.client.OraChatAPIClient.HEADER_AUTHORIZATION;

public class AuthHelper
{
    private static final String TAG = AuthHelper.class.getSimpleName();

    public static void setAuthorizationTokenFromHeaders(Response response)
    {
        for (Header header : response.getHeaders())
        {
            Log.d(TAG, header.getName() + " :: " + header.getValue());

            if (header.getName().equalsIgnoreCase(HEADER_AUTHORIZATION))
            {
                ApplicationSettings.sharedSettings().setLoggedIn();
            }
        }
    }

}
