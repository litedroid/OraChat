package com.litedoid.orachat.api;

import com.google.gson.annotations.Expose;

public class UserData
{
    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private String email;


    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

}