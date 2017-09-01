package com.litedoid.orachat.auth.register;

import android.util.Log;

import com.google.gson.Gson;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.APIErrorType;
import com.litedoid.orachat.api.client.OraChatAPIClient;

public class RegisterPresenter implements RegisterContract.Presenter
{
    private static final String TAG = RegisterPresenter.class.getSimpleName();

    private RegisterContract.View loginView;

    public RegisterPresenter(RegisterContract.View view)
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
    public void register(String email, String name, String password, String confirm)
    {
        new OraChatAPIClient().createUser(email, email, password, confirm, new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "register Error: " + apiErrorType.name());

                loginView.showRegisterFailure();
            }

            @Override
            public void onSuccess(Object o)
            {
                Log.d(TAG, "register Success: " + new Gson().toJson(o));

                loginView.showRegisterSuccess();
            }
        });
    }
}
