package com.litedoid.orachat.api.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ChatMessagesResult
{

    @Expose
    private List<ChatMessage> data;

    @Expose
    private Meta meta;

    public Meta getMeta()
    {
        return meta;
    }

    public List<ChatMessage> getChatMessages()
    {
        return data;
    }

}