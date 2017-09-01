package com.litedoid.orachat.auth.login;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.litedoid.orachat.R;
import com.litedoid.orachat.auth.register.RegisterActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity
{
    private static final String TAG = LoginActivity.class.getSimpleName();

    @ViewById(R.id.left_menu_choice)
    TextView leftMenuChoice;

    LoginFragment loginFragment;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        initLoginFragment();
    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        setMenuOptions();
        showCurrentView();
    }

    private void initLoginFragment()
    {
        loginFragment = LoginFragment_.newInstance();
        loginPresenter = new LoginPresenter(loginFragment);
        loginFragment.setPresenter(loginPresenter);
    }

    @Click(R.id.left_menu_choice)
    protected void onLeftMenuClick()
    {
        //GOTO REGISTER SCREEN
        RegisterActivity_.intent(LoginActivity.this).start();
        finish();
    }

    private void setMenuOptions()
    {
        leftMenuChoice.setText(getString(R.string.register));
    }


    private void showCurrentView()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout, loginFragment);
        fragmentTransaction.commit();
    }
}
