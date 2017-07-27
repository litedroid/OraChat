package com.litedoid.orachat.api.client;

import com.litedoid.orachat.api.CreateUserResult;
import com.litedoid.orachat.api.LoginResult;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface OraChatAPIInterface
{
    String ENDPOINT_USERS = "/users";
    String ENDPOINT_LOGIN = "/auth/login";


    @POST(ENDPOINT_USERS)
    void createUser(@Body Map<String, String> map, Callback<CreateUserResult> callback);

    @POST(ENDPOINT_LOGIN)
    void login(@Body Map<String, String> map, Callback<LoginResult> callback);

}
