package com.litedoid.orachat.api.client;

import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.api.model.ChatMessagesResult;
import com.litedoid.orachat.api.model.CreateUserResult;
import com.litedoid.orachat.api.model.CurrentUserResult;
import com.litedoid.orachat.api.model.LoginResult;
import com.litedoid.orachat.api.model.UpdateUserResult;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface OraChatAPIInterface
{
    String ENDPOINT_USERS = "/users";
    String ENDPOINT_CURRENT_USER = "/users/current";

    String ENDPOINT_LOGIN = "/auth/login";
    String ENDPOINT_LOGOUT = "/auth/logout";

    String ENDPOINT_CHATS = "/chats";
    String ENDPOINT_CHAT_MESSAGES = "/chats/{id}/chat_messages";

    @POST(ENDPOINT_USERS)
    void createUser(@Body Map<String, String> map, Callback<CreateUserResult> callback);

    @GET(ENDPOINT_CURRENT_USER)
    void getCurrentUser(@Header(OraChatAPIClient.HEADER_AUTHORIZATION) String authHeader, Callback<CurrentUserResult> callback);

    @PATCH(ENDPOINT_CURRENT_USER)
    void updateUser(@Header(OraChatAPIClient.HEADER_AUTHORIZATION) String authHeader, @Body Map<String, String> map, Callback<UpdateUserResult> callback);

    @POST(ENDPOINT_LOGIN)
    void login(@Body Map<String, String> map, Callback<LoginResult> callback);

    @POST(ENDPOINT_LOGOUT)
    void logout(@Header(OraChatAPIClient.HEADER_AUTHORIZATION) String authHeader, Callback<Object> callback);

    @GET(ENDPOINT_CHATS)
    void getChatList(@Query(OraChatAPIClient.KEY_PAGE) int page, @Query(OraChatAPIClient.KEY_LIMIT) int limit, Callback<ChatListResult> callback);

    @GET(ENDPOINT_CHAT_MESSAGES)
    void getChatMessages(@Path(OraChatAPIClient.PATH_ID) int id, @Query(OraChatAPIClient.KEY_PAGE) int page, @Query(OraChatAPIClient.KEY_LIMIT) int limit, Callback<ChatMessagesResult> callback);
}