package com.litedoid.orachat.controller.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.litedoid.orachat.R;
import com.litedoid.orachat.interfaces.MainNavigationListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainNavigationListener
{
    private static final String TAG = MainActivity.class.getSimpleName();

    public enum MainScreenView
    {
        CHAT_LIST, CHAT_DETAILS, EDIT_PROFILE
    }

    ChatListFragment chatListFragment;
    ChatDetailsFragment chatDetailsFragment;
    EditProfileFragment editProfileFragment;

    ChatListPresenter chatListPresenter;

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

        createFragments();
    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        setMenuOptions();
        showCurrentView();
        loadData();
    }

    private void createFragments()
    {
        initChatListFragment();
        chatDetailsFragment = ChatDetailsFragment_.newInstance();
        editProfileFragment = EditProfileFragment_.newInstance();
    }

    private void initChatListFragment()
    {
        chatListFragment = ChatListFragment_.newInstance();
        chatListPresenter = new ChatListPresenter(chatListFragment);
        chatListFragment.setPresenter(chatListPresenter);
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

    private void loadData()
    {
        if (currentView == MainScreenView.CHAT_DETAILS)
        {
        }
        else if (currentView == MainScreenView.EDIT_PROFILE)
        {
        }
        else
        {
            chatListPresenter.loadChatContent();
        }
    }

    @Override
    public void onShowChatDetails(int chatId)
    {
        currentView = MainScreenView.CHAT_DETAILS;
        showCurrentView();
    }

    @Override
    public void onShowChatList()
    {
        currentView = MainScreenView.CHAT_LIST;
        showCurrentView();
        loadData();
    }

    @Override
    public void onShowProfile()
    {
        currentView = MainScreenView.EDIT_PROFILE;
        showCurrentView();
    }

}
