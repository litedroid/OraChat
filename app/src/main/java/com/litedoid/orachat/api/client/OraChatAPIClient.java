package com.litedoid.orachat.api.client;

import android.util.Log;

import com.google.gson.Gson;
import com.litedoid.orachat.ApplicationSettings;
import com.litedoid.orachat.Constants;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.APIErrorType;
import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.api.model.ChatMessagesResult;
import com.litedoid.orachat.api.model.CreateUserResult;
import com.litedoid.orachat.api.model.CurrentUserResult;
import com.litedoid.orachat.api.model.LoginResult;
import com.litedoid.orachat.api.model.UpdateUserResult;
import com.litedoid.orachat.helpers.AuthHelper;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

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

    public static final String KEY_PAGE = "page";
    public static final String KEY_LIMIT = "limit";

    // Paths
    public static final String PATH_ID = "id";

    // Headers
    public static final String HEADER_AUTHORIZATION = "Authorization";

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
        Map<String, String> map = new HashMap<>();

        map.put(KEY_NAME, name);
        map.put(KEY_EMAIL, email);
        map.put(KEY_PASSWORD, password);
        map.put(KEY_PASSWORD_CONFIRMATION, passwordConfirmation);

        apiService.createUser(map, new Callback<CreateUserResult>()
        {
            @Override
            public void success(CreateUserResult result, Response response)
            {
                Log.d(TAG, "createUser success: " + new Gson().toJson(result));

                AuthHelper.setAuthorizationTokenFromHeaders(response);

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

    public void getCurrentUser(String header, final APICallback callback)
    {
        apiService.getCurrentUser(header, new Callback<CurrentUserResult>()
        {
            @Override
            public void success(CurrentUserResult result, Response response)
            {
                Log.d(TAG, "getCurrentUser success: " + new Gson().toJson(result));

                callback.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error)
            {
                logRetrofitError("getCurrentUser", error);

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


    public void updateCurrentUser(String header, String name, String email, String password, String passwordConfirmation, final APICallback callback)
    {
        Map<String, String> map = new HashMap<>();

        map.put(KEY_NAME, name);
        map.put(KEY_EMAIL, email);
        map.put(KEY_PASSWORD, password);
        map.put(KEY_PASSWORD_CONFIRMATION, passwordConfirmation);

        apiService.updateUser(header, map, new Callback<UpdateUserResult>()
        {
            @Override
            public void success(UpdateUserResult result, Response response)
            {
                Log.d(TAG, "updateUser success: " + new Gson().toJson(result));

                AuthHelper.setAuthorizationTokenFromHeaders(response);

                callback.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error)
            {
                logRetrofitError("updateUser", error);

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


    public void login(String email, String password, final APICallback callback)
    {
        Map<String, String> map = new HashMap<>();

        map.put(KEY_EMAIL, email);
        map.put(KEY_PASSWORD, password);

        apiService.login(map, new Callback<LoginResult>()
        {
            @Override
            public void success(LoginResult result, Response response)
            {
                Log.d(TAG, "login success: " + new Gson().toJson(result));

                int status = response.getStatus();
                Log.d(TAG, "login status: " + status);

                ApplicationSettings.sharedSettings().setLoggedIn();
                AuthHelper.setAuthorizationTokenFromHeaders(response);

                callback.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error)
            {
                logRetrofitError("login", error);

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

    public void logout(String header, final APICallback callback)
    {
        apiService.logout(header, new Callback<Object>()
        {
            @Override
            public void success(Object o, Response response)
            {
                Log.d(TAG, "logout success");

                int status = response.getStatus();
                Log.d(TAG, "logout status: " + status);

                ApplicationSettings.sharedSettings().clearSession();

                callback.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error)
            {
                logRetrofitError("logout", error);

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

    public void getChatList(int page, int limit, final APICallback callback)
    {
        apiService.getChatList(page, limit, new Callback<ChatListResult>()
        {
            @Override
            public void success(ChatListResult result, Response response)
            {
                Log.d(TAG, "getChatList success: " + new Gson().toJson(result));

                callback.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error)
            {
                logRetrofitError("getChatList", error);

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

    public void getChatMessages(int chatId, int page, int limit, final APICallback callback)
    {
        apiService.getChatMessages(chatId, page, limit, new Callback<ChatMessagesResult>()
        {
            @Override
            public void success(ChatMessagesResult result, Response response)
            {
                Log.d(TAG, "getChatMessages success: " + new Gson().toJson(result));

                callback.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error)
            {
                logRetrofitError("getChatMessages", error);

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