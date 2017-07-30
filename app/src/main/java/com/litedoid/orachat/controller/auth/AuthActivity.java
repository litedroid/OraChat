package com.litedoid.orachat.controller.auth;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.litedoid.orachat.R;
import com.litedoid.orachat.fragment.RegisterFragment;
import com.litedoid.orachat.fragment.RegisterFragment_;
import com.litedoid.orachat.interfaces.AuthNavigationListener;
import com.litedoid.orachat.interfaces.RegisterListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class AuthActivity extends AppCompatActivity implements AuthNavigationListener
{
    private static final String TAG = AuthActivity.class.getSimpleName();

    private boolean showLoginView = true;

    @ViewById(R.id.left_menu_choice)
    TextView leftMenuChoice;

    @ViewById(R.id.right_menu_choice)
    TextView righttMenuChoice;

    LoginFragment loginFragment;
    LoginPresenter loginPresenter;

    RegisterFragment registerFragment;
    RegisterListener registerListener;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        createFragments();
    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        setMenuOptions();
        showCurrentView();
    }

    private void createFragments()
    {
        initLoginFragment();

//        loginListener = loginFragment;

        registerFragment = RegisterFragment_.newInstance();
        registerListener = registerFragment;
    }

    private void initLoginFragment()
    {
        loginFragment = LoginFragment_.newInstance();
        loginPresenter = new LoginPresenter(loginFragment);
        loginFragment.setAuthNavigationListener(this);
        loginFragment.setPresenter(loginPresenter);
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

        if (showLoginView)
        {
            loginPresenter.initiateLogin();
        }
        else
        {
            performRegister();
        }
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
        setMenuOptions();
        showCurrentView();
    }

    private void showCurrentView()
    {

        if (showLoginView)
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_content_layout, loginFragment);
            fragmentTransaction.commit();
        }
        else
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_content_layout, registerFragment);
            fragmentTransaction.commit();
        }
    }

    private void performRegister()
    {
        registerListener.onRegister();

        //temp - send to login
        changeView();
    }

    @Override
    public void onShowRegister()
    {

    }

    @Override
    public void onShowLogin()
    {

    }
}
