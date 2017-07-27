package com.litedoid.orachat.api.model;

import com.google.gson.annotations.Expose;

public class CreateUserResult
{
    @Expose
    private Meta meta;

    public Meta getMeta()
    {
        return meta;
    }

    @Expose
    private User data;

    public User getData()
    {
        return data;
    }

}