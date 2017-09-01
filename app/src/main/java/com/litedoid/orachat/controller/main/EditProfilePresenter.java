package com.litedoid.orachat.controller.main;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.litedoid.orachat.ApplicationSettings;
import com.litedoid.orachat.splash.SplashActivity_;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.APIErrorType;
import com.litedoid.orachat.api.client.OraChatAPIClient;
import com.litedoid.orachat.api.model.CurrentUserResult;

public class EditProfilePresenter implements EditProfileContract.Presenter
{
    private static final String TAG = EditProfilePresenter.class.getSimpleName();

    private EditProfileContract.View editProfileView;

    public EditProfilePresenter(EditProfileContract.View view)
    {
        editProfileView = view;
        editProfileView.setPresenter(this);
    }

    @Override
    public void start()
    {
        Log.d(TAG, "start");
    }

    @Override
    public void loadProfile()
    {
        new OraChatAPIClient().getCurrentUser(ApplicationSettings.sharedSettings().getAuthroizationToken(), new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "loadProfile Error: " + apiErrorType.name());
            }

            @Override
            public void onSuccess(Object o)
            {
                CurrentUserResult result = (CurrentUserResult) o;

                Log.d(TAG, "loadProfile Success: " + new Gson().toJson(o));

                editProfileView.showProfile(result.getUser());
            }
        });
    }

    @Override
    public void logout(Activity activity)
    {
        String authorizationHeader = ApplicationSettings.sharedSettings().getAuthroizationToken();

        ApplicationSettings.sharedSettings().clearSession();

        new OraChatAPIClient().logout(authorizationHeader, new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "logout Error: " + apiErrorType.name());
            }

            @Override
            public void onSuccess(Object o)
            {
                Log.d(TAG, "logout Success");
            }
        });

        SplashActivity_.intent(activity)
                .flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .start();
    }

    @Override
    public void initiateSave()
    {
        editProfileView.initiateSaveProfile();
    }

    @Override
    public void saveProfile(String name, String email, String password, String confirm)
    {
        String authorizationHeader = ApplicationSettings.sharedSettings().getAuthroizationToken();

        new OraChatAPIClient().updateCurrentUser(authorizationHeader, name, email, password, confirm, new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "updateCurrentUser Error: " + apiErrorType.name());

                editProfileView.showSaveProfileFailure();
            }

            @Override
            public void onSuccess(Object o)
            {
                Log.d(TAG, "updateCurrentUser Success: " + new Gson().toJson(o));

                editProfileView.showSaveProfileSuccess();
            }
        });

    }
}
