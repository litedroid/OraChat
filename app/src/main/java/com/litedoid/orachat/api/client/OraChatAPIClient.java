package com.litedoid.orachat.api.client;

import android.util.Log;

import com.google.gson.Gson;
import com.litedoid.orachat.Constants;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.APIErrorType;
import com.litedoid.orachat.api.CreateUserResult;

import java.net.HttpURLConnection;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

public class OraChatAPIClient
{
    private static final String TAG = OraChatAPIClient.class.getSimpleName();

    // Keys
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PASSWORD_CONFIRMATION = "password_confirmation";

    private static RestAdapter restAdapter;
    private OraChatAPIInterface apiService;

    public OraChatAPIClient()
    {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_ENDPOINT)
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(OraChatAPIInterface.class);
    }

    public void createUser(String name, String email, String password, String passwordConfirmation, final APICallback callback)
    {
        apiService.createUser(name, email, password, passwordConfirmation, new Callback<CreateUserResult>()
        {
            @Override
            public void success(CreateUserResult result, Response response)
            {
                Log.d(TAG, "createUser success: " + new Gson().toJson(result));

                callback.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error)
            {
                logRetrofitError("createUser", error);

                if (error.getResponse() == null)
                {
                    callback.onError(APIErrorType.UNKNOWN);
                }
                else if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                {
                    callback.onError(APIErrorType.NETWORK);
                }
                else
                {
                    switch (error.getResponse().getStatus())
                    {
                        case HttpURLConnection.HTTP_NOT_FOUND:
                            callback.onError(APIErrorType.UNKNOWN);
                            break;
                        default:
                            callback.onError(APIErrorType.UNKNOWN);
                            break;
                    }
                }
            }
        });
    }

    private void logRetrofitError(String method, RetrofitError error)
    {
        try
        {
            Log.d(TAG, "retrofitError: " + error.getKind().name());

            if (error.getResponse() != null && error.getResponse().getBody() != null)
            {
                String json = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());

                Log.d(TAG, "API error: " + json);
            }
            else
            {
                Log.d(TAG, "No response or body");
            }

        }
        catch (Exception ex)
        {
            //ignore
        }
    }

}