package com.litedoid.orachat.controller.auth;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.litedoid.orachat.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_newlogin)
public class NewLoginActivity extends AppCompatActivity implements NewLoginView
{
    private static final String TAG = NewLoginActivity.class.getSimpleName();

    @ViewById(R.id.new_username_edittext)
    TextView userNameEditText;

    @ViewById(R.id.new_password_edittext)
    TextView passwordEditText;

    NewLoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        loginPresenter = new NewLoginPresenter(this);
    }

    @AfterViews
    void afterViews()
    {
        Log.d(TAG, "initViews");

    }

    @Click(R.id.new_login_button)
    protected void onLoginButtonClick()
    {
        loginPresenter.login(userNameEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Override
    public void showErrorMessageForBadCredentials()
    {
        Snackbar.make(passwordEditText, "Please check username or password.", BaseTransientBottomBar.LENGTH_LONG).show();

    }

    @Override
    public void showErrorMessageForMaxAttempts()
    {

        Snackbar.make(passwordEditText, "Max login attempts", BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showLoginSuccess()
    {

        Snackbar.make(passwordEditText, "Login Success", BaseTransientBottomBar.LENGTH_LONG).show();
    }
}
