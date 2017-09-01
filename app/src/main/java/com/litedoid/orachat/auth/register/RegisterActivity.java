package com.litedoid.orachat.auth.register;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.litedoid.orachat.R;
import com.litedoid.orachat.auth.login.LoginActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity
{
    private static final String TAG = RegisterActivity.class.getSimpleName();

    @ViewById(R.id.left_menu_choice)
    TextView leftMenuChoice;

    RegisterFragment registerFragment;
    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        initRegisterFragment();
    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        setMenuOptions();
        showCurrentView();
    }

    private void initRegisterFragment()
    {
        registerFragment = RegisterFragment.newInstance();
        registerPresenter = new RegisterPresenter(registerFragment);
        registerFragment.setPresenter(registerPresenter);
    }

    @Click(R.id.left_menu_choice)
    protected void onLeftMenuClick()
    {
        //GOTO LOGIN SCREEN

        LoginActivity_.intent(RegisterActivity.this).start();
        finish();
    }

    private void setMenuOptions()
    {
        leftMenuChoice.setText(getString(R.string.login));
    }

    private void showCurrentView()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout, registerFragment);
        fragmentTransaction.commit();
    }
}
