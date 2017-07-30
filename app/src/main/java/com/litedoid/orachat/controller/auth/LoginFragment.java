package com.litedoid.orachat.controller.auth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.EditText;

import com.litedoid.orachat.BuildConfig;
import com.litedoid.orachat.R;
import com.litedoid.orachat.controller.main.MainActivity_;
import com.litedoid.orachat.helpers.DialogHelper;
import com.litedoid.orachat.interfaces.AuthNavigationListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment implements LoginContract.View
{
    private static final String TAG = LoginFragment.class.getSimpleName();

    @ViewById(R.id.emailEditText)
    EditText emailEditText;

    @ViewById(R.id.passwordEditText)
    EditText passwordEditText;

    private AuthNavigationListener authNavigationListener;

    private LoginContract.Presenter presenter;

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
            emailEditText.setText("test@test.com");
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
    public void setPresenter(LoginContract.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void initiateLogin()
    {
        Log.d(TAG, "initiateLogin");

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        presenter.login(email, password);
    }

    @Override
    public void showLoginSuccess()
    {
        MainActivity_.intent(getActivity()).start();
        getActivity().finish();
    }

    @Override
    public void showLoginFailure()
    {
        //TODO: get error passed back from server and display
        DialogHelper.showOKDialog(getActivity(), R.string.login_error_title, R.string.login_error_message);
    }

    @Override
    public void setAuthNavigationListener(AuthNavigationListener authNavigationListener)
    {
        this.authNavigationListener = authNavigationListener;
    }
}