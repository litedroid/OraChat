package com.litedoid.orachat.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.litedoid.orachat.R;
import com.litedoid.orachat.fragment.ChatDetailsFragment;
import com.litedoid.orachat.fragment.ChatDetailsFragment_;
import com.litedoid.orachat.fragment.ChatListFragment;
import com.litedoid.orachat.fragment.ChatListFragment_;
import com.litedoid.orachat.fragment.EditProfileFragment;
import com.litedoid.orachat.fragment.EditProfileFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    public enum MainScreenView
    {
        CHAT_LIST, CHAT_DETAILS, EDIT_PROFILE
    }

    ChatListFragment chatListFragment;
    ChatDetailsFragment chatDetailsFragment;
    EditProfileFragment editProfileFragment;

    @ViewById(R.id.account_bottom_menu_choice)
    Button accountButton;

    @ViewById(R.id.chatlist_bottom_menu_choice)
    Button chatListButton;

    @ViewById(R.id.left_menu_choice)
    TextView leftMenuChoice;

    @ViewById(R.id.menu_title)
    TextView menuTitle;

    @ViewById(R.id.right_menu_choice)
    TextView rightMenuChoice;

    private MainScreenView currentView = MainScreenView.CHAT_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        chatListFragment = ChatListFragment_.newInstance();
        chatDetailsFragment = ChatDetailsFragment_.newInstance();
        editProfileFragment = EditProfileFragment_.newInstance();
    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        setMenuOptions();
        showCurrentView();
    }

    private void setMenuOptions()
    {
        if (currentView == MainScreenView.CHAT_DETAILS)
        {
            leftMenuChoice.setVisibility(View.VISIBLE);
            rightMenuChoice.setVisibility(View.GONE);
        }
        else if (currentView == MainScreenView.EDIT_PROFILE)
        {
            leftMenuChoice.setVisibility(View.GONE);
            rightMenuChoice.setVisibility(View.VISIBLE);

        }
        else
        {
            leftMenuChoice.setVisibility(View.GONE);
            rightMenuChoice.setVisibility(View.GONE);
        }
    }


    private void showCurrentView()
    {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (currentView == MainScreenView.CHAT_DETAILS)
        {
            fragmentTransaction.replace(R.id.main_content_layout, chatDetailsFragment);
        }
        else if (currentView == MainScreenView.EDIT_PROFILE)
        {
            fragmentTransaction.replace(R.id.main_content_layout, editProfileFragment);
        }
        else
        {
            fragmentTransaction.replace(R.id.main_content_layout, chatListFragment);
        }

        fragmentTransaction.commit();
    }


}
