package com.litedoid.orachat.chat.editprofile;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.litedoid.orachat.R;
import com.litedoid.orachat.chat.ChatBaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class EditProfileActivity extends ChatBaseActivity
{
    private static final String TAG = EditProfileActivity.class.getSimpleName();

    EditProfileFragment editProfileFragment;
    EditProfilePresenter editProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        currentView = ChatScreenView.EDIT_PROFILE;

        initEditProfileFragment();
    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        showCurrentView();
        setNavigationOptions();
    }


    private void initEditProfileFragment()
    {
        editProfileFragment = EditProfileFragment_.newInstance();
        editProfilePresenter = new EditProfilePresenter(editProfileFragment);
        editProfileFragment.setPresenter(editProfilePresenter);
    }

    private void showCurrentView()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout, editProfileFragment);

        fragmentTransaction.commit();
    }

}
