package com.litedoid.orachat.chat.editprofile;


import android.app.Activity;

import com.litedoid.orachat.api.model.User;
import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;

public interface EditProfileContract
{
    interface View extends BaseView<Presenter>
    {
        void showProfile(User user);

        void showSaveProfileSuccess();

        void showSaveProfileFailure();

        void showLogoutMessage();
    }

    interface Presenter extends BasePresenter
    {
        void loadProfile();

        void logout(Activity activity);

        void saveProfile(String name, String email, String password, String confirm);
    }
}
