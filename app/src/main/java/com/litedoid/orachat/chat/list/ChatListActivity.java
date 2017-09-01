package com.litedoid.orachat.chat.list;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.litedoid.orachat.R;
import com.litedoid.orachat.chat.ChatBaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class ChatListActivity extends ChatBaseActivity
{
    private static final String TAG = ChatListActivity.class.getSimpleName();

    ChatListFragment chatListFragment;
    ChatListPresenter chatListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        currentView = ChatScreenView.CHAT_LIST;

        initChatListFragment();
    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        showCurrentView();
        setNavigationOptions();
    }

    private void initChatListFragment()
    {
        chatListFragment = ChatListFragment_.newInstance();
        chatListPresenter = new ChatListPresenter(chatListFragment);
        chatListFragment.setPresenter(chatListPresenter);
    }


    private void showCurrentView()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout, chatListFragment);
        fragmentTransaction.commit();
    }

//    private void setNavigationOptions()
//    {
//        accountButton.setSelected(false);
//        chatListButton.setSelected(true);
//
//        menuTitle.setText(getString(R.string.app_name));
//
//        leftMenuChoice.setVisibility(View.GONE);
//        rightMenuChoice.setVisibility(View.GONE);
//    }

//    @Override
//    public void onLoadChatDetails(int chatId, String chatTitle)
//    {
//        Log.d(TAG, "onLoadChatDetails: " + chatId);
//
//        currentView = ChatScreenView.CHAT_DETAILS;
//        showCurrentView();
//
//        chatDetailsPresenter.loadChatDetails(chatId);
//
//        menuTitle.setText(chatTitle);
//    }

}
