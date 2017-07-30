package com.litedoid.orachat.api.model;

import com.google.gson.annotations.Expose;

public class CurrentUserResult
{

    @Expose
    private User data;

    @Expose
    private Meta meta;

    public Meta getMeta()
    {
        return meta;
    }

    public User getUser()
    {
        return data;
    }

}