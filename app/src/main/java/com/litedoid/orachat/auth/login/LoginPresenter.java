package com.litedoid.orachat.auth.login;

import android.util.Log;

import com.google.gson.Gson;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.APIErrorType;
import com.litedoid.orachat.api.client.OraChatAPIClient;

public class LoginPresenter implements LoginContract.Presenter
{
    private static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginContract.View loginView;

    public LoginPresenter(LoginContract.View view)
    {
        loginView = view;
        loginView.setPresenter(this);
    }

    @Override
    public void start()
    {
        Log.d(TAG, "start");
    }

    @Override
    public void login(String email, String password)
    {
        new OraChatAPIClient().login(email, password, new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "login Error: " + apiErrorType.name());

                loginView.showLoginFailure();
            }

            @Override
            public void onSuccess(Object o)
            {
                Log.d(TAG, "login Success: " + new Gson().toJson(o));

                loginView.showLoginSuccess();
            }
        });
    }
}
