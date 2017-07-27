package com.litedoid.orachat.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatMessage
{
    @Expose
    private int id;

    @SerializedName("chat_id")
    @Expose
    private int chatId;

    @SerializedName("user_id")
    @Expose
    private int userId;

    @Expose
    private String message;

    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Expose
    private User user;

    public int getId()
    {
        return id;
    }

    public int getChatId()
    {
        return chatId;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getMessage()
    {
        return message;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public User getUser()
    {
        return user;
    }
}