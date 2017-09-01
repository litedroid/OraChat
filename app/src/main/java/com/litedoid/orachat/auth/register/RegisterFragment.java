package com.litedoid.orachat.auth.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.EditText;

import com.litedoid.orachat.R;
import com.litedoid.orachat.chat.list.ChatListActivity_;
import com.litedoid.orachat.helpers.DialogHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_register)
public class RegisterFragment extends Fragment implements RegisterContract.View
{
    private static final String TAG = RegisterFragment.class.getSimpleName();

    @ViewById(R.id.nameEditText)
    EditText nameEditText;

    @ViewById(R.id.emailEditText)
    EditText emailEditText;

    @ViewById(R.id.passwordEditText)
    EditText passwordEditText;

    @ViewById(R.id.confirmEditText)
    EditText confirmEditText;

    private RegisterContract.Presenter presenter;

    public static RegisterFragment newInstance()
    {
        return new RegisterFragment_();
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
    public void setPresenter(RegisterContract.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Click(R.id.register_button)
    public void onClickRegister()
    {
        Log.d(TAG, "onClickRegister");

        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirm = confirmEditText.getText().toString().trim();

        presenter.register(name, email, password, confirm);
    }

    @Override
    public void showRegisterSuccess()
    {
        ChatListActivity_.intent(getActivity()).start();
        getActivity().finish();
    }

    @Override
    public void showRegisterFailure()
    {
        //TODO: get error passed back from server and display
        DialogHelper.showOKDialog(getActivity(), R.string.register_error_title, R.string.register_error_message);
    }

}