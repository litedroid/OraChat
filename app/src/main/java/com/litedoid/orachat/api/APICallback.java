package com.litedoid.orachat.api;

public abstract class APICallback
{
    public abstract void onError(APIErrorType apiErrorType);

    public abstract void onSuccess(Object o);
}