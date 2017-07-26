package com.litedoid.orachat.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.litedoid.orachat.R;
import com.litedoid.orachat.fragment.LoginFragment;
import com.litedoid.orachat.fragment.LoginFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginOrRegisterActivity extends AppCompatActivity
{
    private static final String TAG = LoginOrRegisterActivity.class.getSimpleName();

    private boolean showLoginView = true;

    @ViewById(R.id.left_menu_choice)
    TextView leftMenuChoice;

    @ViewById(R.id.right_menu_choice)
    TextView righttMenuChoice;

    LoginFragment_ loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        setMenuOptions();
        showCurrentView();
    }

    @Click(R.id.left_menu_choice)
    protected void onLeftMenuClick()
    {
        Log.d(TAG, "onLeftMenuClick showLoginView: " + showLoginView);

        changeView();
    }

    @Click(R.id.right_menu_choice)
    protected void onRightMenuClick()
    {
        Log.d(TAG, "onRightMenuClick showLoginView: " + showLoginView);

    }

    private void setMenuOptions()
    {
        if (showLoginView)
        {
            leftMenuChoice.setText(getString(R.string.register));
            righttMenuChoice.setText(getString(R.string.login));
        }
        else
        {
            leftMenuChoice.setText(getString(R.string.login));
            righttMenuChoice.setText(getString(R.string.register));
        }
    }

    private void changeView()
    {
        showLoginView = !showLoginView;
    }

    private void showCurrentView()
    {

        if (showLoginView)
        {
            LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.main_content_layout);
            if (fragment == null)
            {
                fragment = LoginFragment_.newInstance();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_content_layout, fragment);
                fragmentTransaction.commit();
            }
        }
    }
}
