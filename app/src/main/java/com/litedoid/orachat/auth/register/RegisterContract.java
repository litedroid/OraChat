package com.litedoid.orachat.auth.register;

import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;

public interface RegisterContract
{
    interface View extends BaseView<Presenter>
    {
        void showRegisterSuccess();

        void showRegisterFailure();
    }

    interface Presenter extends BasePresenter
    {
        void register(String name, String email, String password, String confirm);
    }
}
