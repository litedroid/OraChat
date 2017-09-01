package com.litedoid.orachat.auth.login;

import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;

public interface LoginContract
{
    interface View extends BaseView<Presenter>
    {
        void showLoginSuccess();

        void showLoginFailure();
    }

    interface Presenter extends BasePresenter
    {
        void login(String name, String password);
    }
}
