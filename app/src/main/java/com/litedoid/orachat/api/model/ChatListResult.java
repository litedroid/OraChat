package com.litedoid.orachat.api.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ChatListResult
{

    @Expose
    private Data data;

    @Expose
    private Meta meta;

    public Meta getMeta()
    {
        return meta;
    }

    public Data getData()
    {
        return data;
    }

    public class Data
    {
        @Expose
        private int id;

        @Expose
        private String name;

        @Expose
        private List<User> users;

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