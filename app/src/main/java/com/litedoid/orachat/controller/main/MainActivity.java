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
import org.androidannotations.annotations.Click;
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
    ChatDetailsPresenter chatDetailsPresenter;
    EditProfilePresenter editProfilePresenter;

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

        showCurrentView();
    }

    private void createFragments()
    {
        initChatListFragment();
        initChatDetailsFragment();
        initEditProfileFragment();
    }

    private void initChatListFragment()
    {
        chatListFragment = ChatListFragment_.newInstance();
        chatListPresenter = new ChatListPresenter(chatListFragment);
        chatListFragment.setMainNavigationListener(this);
        chatListFragment.setPresenter(chatListPresenter);
    }

    private void initChatDetailsFragment()
    {
        chatDetailsFragment = ChatDetailsFragment_.newInstance();
        chatDetailsPresenter = new ChatDetailsPresenter(chatDetailsFragment);
        chatDetailsFragment.setPresenter(chatDetailsPresenter);
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

        setNavigationOptions();
    }

    private void setNavigationOptions()
    {
        if (currentView == MainScreenView.CHAT_DETAILS)
        {
            accountButton.setSelected(false);
            chatListButton.setSelected(true);

            leftMenuChoice.setVisibility(View.VISIBLE);
            rightMenuChoice.setVisibility(View.GONE);
        }
        else if (currentView == MainScreenView.EDIT_PROFILE)
        {
            menuTitle.setText(getString(R.string.account));

            accountButton.setSelected(true);
            chatListButton.setSelected(false);

            leftMenuChoice.setVisibility(View.VISIBLE);
            rightMenuChoice.setVisibility(View.VISIBLE);
        }
        else
        {
            accountButton.setSelected(false);
            chatListButton.setSelected(true);

            menuTitle.setText(getString(R.string.app_name));

            leftMenuChoice.setVisibility(View.GONE);
            rightMenuChoice.setVisibility(View.GONE);
        }
    }

    @Click(R.id.account_bottom_menu_choice)
    protected void onClickAccountButton()
    {
        onShowProfile();
    }

    @Click(R.id.chatlist_bottom_menu_choice)
    protected void onClickChatList()
    {
        onShowChatList();
    }

    @Click(R.id.left_menu_choice)
    protected void onClickLeftMenuButton()
    {
        performBackNavigation();
    }

    @Click(R.id.right_menu_choice)
    protected void onClickRightMenuButton()
    {
        if (currentView == MainScreenView.EDIT_PROFILE)
        {
            editProfilePresenter.initiateSave();
        }
    }

    @Override
    public void onLoadChatDetails(int chatId, String chatTitle)
    {
        Log.d(TAG, "onLoadChatDetails: " + chatId);

        currentView = MainScreenView.CHAT_DETAILS;
        showCurrentView();

        chatDetailsPresenter.loadChatDetails(chatId);

        menuTitle.setText(chatTitle);
    }

    @Override
    public void onShowChatList()
    {
        currentView = MainScreenView.CHAT_LIST;
        showCurrentView();
    }

    @Override
    public void onShowProfile()
    {
        currentView = MainScreenView.EDIT_PROFILE;
        showCurrentView();
    }

    @Override
    public void onBackPressed()
    {
        performBackNavigation();
    }

    private void performBackNavigation()
    {
        if (currentView == MainScreenView.CHAT_LIST)
            finish();
        else
            currentView = MainScreenView.CHAT_LIST;

        showCurrentView();
    }

}
