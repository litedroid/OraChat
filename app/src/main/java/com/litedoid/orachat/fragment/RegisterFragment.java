package com.litedoid.orachat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.litedoid.orachat.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_login)
public class RegisterFragment extends Fragment
{
    private static final String TAG = RegisterFragment.class.getSimpleName();

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

}