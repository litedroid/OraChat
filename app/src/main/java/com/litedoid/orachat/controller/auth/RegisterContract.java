package com.litedoid.orachat.controller.auth;

import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;
import com.litedoid.orachat.interfaces.AuthNavigationListener;

public interface RegisterContract
{
    interface View extends BaseView<Presenter>
    {
        void initiateRegister();

        void showRegisterSuccess();

        void showRegisterFailure();

        void setAuthNavigationListener(AuthNavigationListener authNavigationListener);
    }

    interface Presenter extends BasePresenter
    {
        void initiateRegister();

        void register(String name, String email, String password, String confirm);
    }
}
