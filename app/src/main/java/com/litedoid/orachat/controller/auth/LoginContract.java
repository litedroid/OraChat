package com.litedoid.orachat.controller.auth;


import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;
import com.litedoid.orachat.interfaces.AuthNavigationListener;

public interface LoginContract
{
    interface View extends BaseView<Presenter>
    {
        void initiateLogin();

        void showLoginSuccess();

        void showLoginFailure();

        void setAuthNavigationListener(AuthNavigationListener authNavigationListener);
    }

    interface Presenter extends BasePresenter
    {
        void initiateLogin();

        void login(String name, String password);
    }
}
