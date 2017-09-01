package com.litedoid.orachat.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.litedoid.orachat.R;
import com.litedoid.orachat.chat.editprofile.EditProfileActivity_;
import com.litedoid.orachat.chat.list.ChatListActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class ChatBaseActivity extends AppCompatActivity
{
    private static final String TAG = ChatBaseActivity.class.getSimpleName();

    public enum ChatScreenView
    {
        CHAT_LIST, CHAT_DETAILS, EDIT_PROFILE
    }

    @ViewById(R.id.account_bottom_menu_choice)
    protected Button accountButton;

    @ViewById(R.id.chatlist_bottom_menu_choice)
    protected Button chatListButton;

    @ViewById(R.id.left_menu_choice)
    protected TextView leftMenuChoice;

    @ViewById(R.id.menu_title)
    protected TextView menuTitle;

    @ViewById(R.id.right_menu_choice)
    protected TextView rightMenuChoice;

    protected ChatScreenView currentView = ChatScreenView.CHAT_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.account_bottom_menu_choice)
    protected void onClickAccountButton()
    {
        if (currentView == ChatScreenView.EDIT_PROFILE)
            return;

        EditProfileActivity_.intent(ChatBaseActivity.this).start();
        finish();

    }

    @Click(R.id.chatlist_bottom_menu_choice)
    protected void onClickChatList()
    {
        if (currentView == ChatScreenView.CHAT_LIST)
            return;

        ChatListActivity_.intent(ChatBaseActivity.this).start();
        finish();
    }

    @Click(R.id.left_menu_choice)
    protected void onClickLeftMenuButton()
    {
        performBackNavigation();
    }

    @Click(R.id.right_menu_choice)
    protected void onClickRightMenuButton()
    {

    }

    @Override
    public void onBackPressed()
    {
        performBackNavigation();
    }

    private void performBackNavigation()
    {
        finish();
    }

    protected void setNavigationOptions()
    {
        if (currentView == ChatScreenView.CHAT_DETAILS)
        {
            accountButton.setSelected(false);
            chatListButton.setSelected(true);

            leftMenuChoice.setVisibility(View.VISIBLE);
            rightMenuChoice.setVisibility(View.GONE);
        }
        else if (currentView == ChatScreenView.EDIT_PROFILE)
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
}
