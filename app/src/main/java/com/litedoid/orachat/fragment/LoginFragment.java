package com.litedoid.orachat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.litedoid.orachat.BuildConfig;
import com.litedoid.orachat.R;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.APIErrorType;
import com.litedoid.orachat.api.client.OraChatAPIClient;
import com.litedoid.orachat.interfaces.LoginListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment implements LoginListener
{
    private static final String TAG = LoginFragment.class.getSimpleName();

    @ViewById(R.id.emailEditText)
    EditText emailEditText;

    @ViewById(R.id.passwordEditText)
    EditText passwordEditText;

    public static LoginFragment newInstance()
    {
        return new LoginFragment_();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews()
    {
        Log.d(TAG, "afterViews");

        if (BuildConfig.DEBUG)
        {
            emailEditText.setText("litedroiddevelopment@gmail.com");
            passwordEditText.setText("password");
        }
    }

    @Override
    public void onResume()
    {
        Log.d(TAG, "onResume");
        super.onResume();

    }

    @Override
    public void onPause()
    {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onLogin()
    {
        Log.d(TAG, "onLogin");

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        new OraChatAPIClient().login(email, password, new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "login Error: " + apiErrorType.name());

            }

            @Override
            public void onSuccess(Object o)
            {

                Log.d(TAG, "login Success: " + new Gson().toJson(o));

            }
        });
    }
}