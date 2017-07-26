package com.litedoid.orachat.api;

import com.google.gson.annotations.Expose;

public class CreateUserResult
{
    @Expose
    private Meta meta;

    public Meta getMeta()
    {
        return meta;
    }

}