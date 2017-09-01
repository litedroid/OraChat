package com.litedoid.orachat.api.client;

import android.util.Log;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.litedoid.orachat.ApplicationSettings;
import com.litedoid.orachat.Constants;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.api.model.ChatMessagesResult;
import com.litedoid.orachat.api.model.CreateUserResult;
import com.litedoid.orachat.api.model.CurrentUserResult;
import com.litedoid.orachat.api.model.LoginResult;
import com.litedoid.orachat.api.model.UpdateUserResult;
import com.litedoid.orachat.helpers.AuthHelper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

    private OraChatAPIInterface apiService;

    public OraChatAPIClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(OraChatAPIInterface.class);
    }

    public void createUser(String name, String email, String password, String passwordConfirmation, final APICallback callback)
    {
        Map<String, String> map = new HashMap<>();

        map.put(KEY_NAME, name);
        map.put(KEY_EMAIL, email);
        map.put(KEY_PASSWORD, password);
        map.put(KEY_PASSWORD_CONFIRMATION, passwordConfirmation);

        Call<CreateUserResult> call = apiService.createUser(map);

        call.enqueue(new Callback<CreateUserResult>()
        {
            @Override
            public void onResponse(Call<CreateUserResult> call, Response<CreateUserResult> response)
            {
                Log.d(TAG, "createUser success: " + new Gson().toJson(response));

                AuthHelper.setAuthorizationTokenFromHeaders(response);

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CreateUserResult> call, Throwable t)
            {
//                logRetrofitError("createUser", error);
//
//                if (error.getResponse() == null)
//                {
//                    callback.onError(APIErrorType.UNKNOWN);
//                }
//                else if (error.getKind().equals(RetrofitError.Kind.NETWORK))
//                {
//                    callback.onError(APIErrorType.NETWORK);
//                }
//                else
//                {
//                    switch (error.getResponse().getStatus())
//                    {
//                        case HttpURLConnection.HTTP_NOT_FOUND:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                        default:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                    }
//                }
            }

        });
    }

    public void getCurrentUser(String header, final APICallback callback)
    {
        Call<CurrentUserResult> call = apiService.getCurrentUser(header);

        call.enqueue(new Callback<CurrentUserResult>()
        {
            @Override
            public void onResponse(Call<CurrentUserResult> call, Response<CurrentUserResult> response)
            {
                Log.d(TAG, "getCurrentUser success: " + new Gson().toJson(response));

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentUserResult> call, Throwable t)
            {
//                logRetrofitError("getCurrentUser", error);
//
//                if (error.getResponse() == null)
//                {
//                    callback.onError(APIErrorType.UNKNOWN);
//                }
//                else if (error.getKind().equals(RetrofitError.Kind.NETWORK))
//                {
//                    callback.onError(APIErrorType.NETWORK);
//                }
//                else
//                {
//                    switch (error.getResponse().getStatus())
//                    {
//                        case HttpURLConnection.HTTP_NOT_FOUND:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                        default:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                    }
//                }
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

        Call<UpdateUserResult> call = apiService.updateUser(header, map);

        call.enqueue(new Callback<UpdateUserResult>()
        {
            @Override
            public void onResponse(Call<UpdateUserResult> call, Response<UpdateUserResult> response)
            {
                Log.d(TAG, "updateUser success: " + new Gson().toJson(response.body()));

                AuthHelper.setAuthorizationTokenFromHeaders(response);

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UpdateUserResult> call, Throwable t)
            {
//                logRetrofitError("updateUser", error);
//
//                if (error.getResponse() == null)
//                {
//                    callback.onError(APIErrorType.UNKNOWN);
//                }
//                else if (error.getKind().equals(RetrofitError.Kind.NETWORK))
//                {
//                    callback.onError(APIErrorType.NETWORK);
//                }
//                else
//                {
//                    switch (error.getResponse().getStatus())
//                    {
//                        case HttpURLConnection.HTTP_NOT_FOUND:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                        default:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                    }
//                }
            }

        });
    }


    public void login(String email, String password, final APICallback callback)
    {
        Map<String, String> map = new HashMap<>();

        map.put(KEY_EMAIL, email);
        map.put(KEY_PASSWORD, password);

        Call<LoginResult> call = apiService.login(map);

        call.enqueue(new Callback<LoginResult>()
        {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response)
            {
                Log.d(TAG, "login success: " + new Gson().toJson(response.body()));

                int status = response.code();
                Log.d(TAG, "login status: " + status);

                ApplicationSettings.sharedSettings().setLoggedIn();
                AuthHelper.setAuthorizationTokenFromHeaders(response);

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t)
            {
//                logRetrofitError("login", error);
//
//                if (error.getResponse() == null)
//                {
//                    callback.onError(APIErrorType.UNKNOWN);
//                }
//                else if (error.getKind().equals(RetrofitError.Kind.NETWORK))
//                {
//                    callback.onError(APIErrorType.NETWORK);
//                }
//                else
//                {
//                    switch (error.getResponse().getStatus())
//                    {
//                        case HttpURLConnection.HTTP_NOT_FOUND:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                        default:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                    }
//                }
            }

        });
    }

    public void logout(String header, final APICallback callback)
    {
        Call<Object> call = apiService.logout(header);

        call.enqueue(new Callback<Object>()
        {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response)
            {
                Log.d(TAG, "logout success");

                int status = response.code();
                Log.d(TAG, "logout status: " + status);

                ApplicationSettings.sharedSettings().clearSession();

                callback.onSuccess(null);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t)
            {
//                logRetrofitError("logout", error);
//
//                if (error.getResponse() == null)
//                {
//                    callback.onError(APIErrorType.UNKNOWN);
//                }
//                else if (error.getKind().equals(RetrofitError.Kind.NETWORK))
//                {
//                    callback.onError(APIErrorType.NETWORK);
//                }
//                else
//                {
//                    switch (error.getResponse().getStatus())
//                    {
//                        case HttpURLConnection.HTTP_NOT_FOUND:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                        default:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                    }
//                }
            }

        });
    }

    public void getChatList(int page, int limit, final APICallback callback)
    {
        Call<ChatListResult> call = apiService.getChatList(page, limit);

        call.enqueue(new Callback<ChatListResult>()
        {
            @Override
            public void onResponse(Call<ChatListResult> call, Response<ChatListResult> response)
            {
                Log.d(TAG, "getChatList success: " + new Gson().toJson(response.body()));

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ChatListResult> call, Throwable t)
            {
//                logRetrofitError("getChatList", error);
//
//                if (error.getResponse() == null)
//                {
//                    callback.onError(APIErrorType.UNKNOWN);
//                }
//                else if (error.getKind().equals(RetrofitError.Kind.NETWORK))
//                {
//                    callback.onError(APIErrorType.NETWORK);
//                }
//                else
//                {
//                    switch (error.getResponse().getStatus())
//                    {
//                        case HttpURLConnection.HTTP_NOT_FOUND:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                        default:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                    }
//                }
            }

        });
    }

    public void getChatMessages(int chatId, int page, int limit, final APICallback callback)
    {
        Call<ChatMessagesResult> call = apiService.getChatMessages(chatId, page, limit);

        call.enqueue(new Callback<ChatMessagesResult>()
        {
            @Override
            public void onResponse(Call<ChatMessagesResult> call, Response<ChatMessagesResult> response)
            {
                Log.d(TAG, "getChatMessages success: " + new Gson().toJson(response.body()));

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ChatMessagesResult> call, Throwable t)
            {
//                logRetrofitError("getChatMessages", error);
//
//                if (error.getResponse() == null)
//                {
//                    callback.onError(APIErrorType.UNKNOWN);
//                }
//                else if (error.getKind().equals(RetrofitError.Kind.NETWORK))
//                {
//                    callback.onError(APIErrorType.NETWORK);
//                }
//                else
//                {
//                    switch (error.getResponse().getStatus())
//                    {
//                        case HttpURLConnection.HTTP_NOT_FOUND:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                        default:
//                            callback.onError(APIErrorType.UNKNOWN);
//                            break;
//                    }
//                }
            }

        });
    }

//    private void logRetrofitError(String method, RetrofitError error)
//    {
//        try
//        {
//            Log.d(TAG, "retrofitError: " + error.getKind().name());
//
//            if (error.getResponse() != null && error.getResponse().getBody() != null)
//            {
//                String json = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
//
//                Log.d(TAG, "API error: " + json);
//            }
//            else
//            {
//                Log.d(TAG, "No response or body");
//            }
//
//        }
//        catch (Exception ex)
//        {
//            //ignore
//        }
//    }

}