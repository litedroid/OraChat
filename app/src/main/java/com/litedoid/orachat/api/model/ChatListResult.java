package com.litedoid.orachat.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatListResult
{

    @Expose
    private List<Chat> data;

    @Expose
    private Meta meta;

    public Meta getMeta()
    {
        return meta;
    }

    public List<Chat> getChats()
    {
        return data;
    }

    public class Chat
    {
        @Expose
        private int id;

        @Expose
        private String name;

        @Expose
        private List<User> users;

        @SerializedName("last_chat_message")
        @Expose
        private ChatMessage lastChatMessage;

        public int getId()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public List<User> getUsers()
        {
            return users;
        }

        public ChatMessage getLastChatMessage()
        {
            return lastChatMessage;
        }
    }
}