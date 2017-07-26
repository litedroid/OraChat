package com.litedoid.orachat.api.client;

import com.litedoid.orachat.api.CreateUserResult;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.POST;

public interface OraChatAPIInterface
{
    String ENDPOINT_USERS = "/users";

    @POST(ENDPOINT_USERS)
    void createUser(@Field(OraChatAPIClient.KEY_NAME) String name,
                    @Field(OraChatAPIClient.KEY_EMAIL) String email,
                    @Field(OraChatAPIClient.KEY_PASSWORD) String password,
                    @Field(OraChatAPIClient.KEY_PASSWORD_CONFIRMATION) String passwordConfirmation,
                    Callback<CreateUserResult> callback);


}
