package com.litedoid.orachat.api.client;

import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.api.model.ChatMessagesResult;
import com.litedoid.orachat.api.model.CreateUserResult;
import com.litedoid.orachat.api.model.CurrentUserResult;
import com.litedoid.orachat.api.model.LoginResult;
import com.litedoid.orachat.api.model.UpdateUserResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OraChatAPIInterface
{
    String ENDPOINT_USERS = "/users";
    String ENDPOINT_CURRENT_USER = "/users/current";

    String ENDPOINT_LOGIN = "/auth/login";
    String ENDPOINT_LOGOUT = "/auth/logout";

    String ENDPOINT_CHATS = "/chats";
    String ENDPOINT_CHAT_MESSAGES = "/chats/{id}/chat_messages";

    @POST(ENDPOINT_USERS)
    Call<CreateUserResult> createUser(@Body Map<String, String> map);

    @GET(ENDPOINT_CURRENT_USER)
    Call<CurrentUserResult> getCurrentUser(@Header(OraChatAPIClient.HEADER_AUTHORIZATION) String authHeader);

    @PATCH(ENDPOINT_CURRENT_USER)
    Call<UpdateUserResult> updateUser(@Header(OraChatAPIClient.HEADER_AUTHORIZATION) String authHeader, @Body Map<String, String> map);

    @POST(ENDPOINT_LOGIN)
    Call<LoginResult> login(@Body Map<String, String> map);

    @POST(ENDPOINT_LOGOUT)
    Call<Object> logout(@Header(OraChatAPIClient.HEADER_AUTHORIZATION) String authHeader);

    @GET(ENDPOINT_CHATS)
    Call<ChatListResult> getChatList(@Query(OraChatAPIClient.KEY_PAGE) int page, @Query(OraChatAPIClient.KEY_LIMIT) int limit);

    @GET(ENDPOINT_CHAT_MESSAGES)
    Call<ChatMessagesResult> getChatMessages(@Path(OraChatAPIClient.PATH_ID) int id, @Query(OraChatAPIClient.KEY_PAGE) int page, @Query(OraChatAPIClient.KEY_LIMIT) int limit);
}