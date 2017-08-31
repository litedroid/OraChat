package com.litedoid.orachat.controller.auth;

public class NewLoginPresenter
{
    private static final String TAG = NewLoginPresenter.class.getSimpleName();

    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private int loginAttempts;

    NewLoginView loginView;

    public NewLoginPresenter(NewLoginView loginView)
    {
        this.loginView = loginView;
    }

    public int incrementLoginAttempt()
    {
        return ++loginAttempts;
    }

    public boolean isLoginAttemptsExceeded()
    {
        return loginAttempts >= MAX_LOGIN_ATTEMPTS;
    }

    public boolean isLoginAttemptsNotExceeded()
    {
        return loginAttempts < MAX_LOGIN_ATTEMPTS;
    }

    public void login(String userName, String password)
    {
        if (isLoginAttemptsExceeded())
            loginView.showErrorMessageForMaxAttempts();

        if (userName.equals("andy") && password.equals("password"))
            loginView.showLoginSuccess();

        incrementLoginAttempt();

        loginView.showErrorMessageForBadCredentials();
    }
}
