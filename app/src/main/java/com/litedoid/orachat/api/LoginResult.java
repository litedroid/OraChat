package com.litedoid.orachat.api;

import com.google.gson.annotations.Expose;

public class LoginResult
{
    @Expose
    private Meta meta;

    public Meta getMeta()
    {
        return meta;
    }

    @Expose
    private UserData data;

    public UserData getData()
    {
        return data;
    }
}