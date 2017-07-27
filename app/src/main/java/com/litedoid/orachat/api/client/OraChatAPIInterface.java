package com.litedoid.orachat.api.client;

import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.api.model.CreateUserResult;
import com.litedoid.orachat.api.model.LoginResult;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface OraChatAPIInterface
{
    String ENDPOINT_USERS = "/users";
    String ENDPOINT_LOGIN = "/auth/login";

    String ENDPOINT_CHATS = "/chats";

    @POST(ENDPOINT_USERS)
    void createUser(@Body Map<String, String> map, Callback<CreateUserResult> callback);

    @POST(ENDPOINT_LOGIN)
    void login(@Body Map<String, String> map, Callback<LoginResult> callback);

    @GET(ENDPOINT_CHATS)
    void getChatList(@Query(OraChatAPIClient.KEY_PAGE) int page, @Query(OraChatAPIClient.KEY_LIMIT) int limit, Callback<ChatListResult> callback);

}
