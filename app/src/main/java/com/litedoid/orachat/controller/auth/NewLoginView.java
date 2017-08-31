package com.litedoid.orachat.controller.auth;

public interface NewLoginView
{
    void showErrorMessageForBadCredentials();

    void showErrorMessageForMaxAttempts();

    void showLoginSuccess();

}
