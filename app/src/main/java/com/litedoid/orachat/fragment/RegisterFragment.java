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
import com.litedoid.orachat.interfaces.RegisterListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_register)
public class RegisterFragment extends Fragment implements RegisterListener
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

        if(BuildConfig.DEBUG)
        {
            nameEditText.setText("Andy");
            emailEditText.setText("litedroiddevelopment@gmail.com");
            passwordEditText.setText("password");
            confirmEditText.setText("password");
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
    public void onRegister()
    {
        Log.d(TAG, "onRegister");
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirm = confirmEditText.getText().toString().trim();

        new OraChatAPIClient().createUser(name, email, password, confirm, new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "createUser Error: " + apiErrorType.name());

            }

            @Override
            public void onSuccess(Object o)
            {

                Log.d(TAG, "createUser Success: " + new Gson().toJson(o));

            }
        });
    }

}