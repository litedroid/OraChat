package com.litedoid.orachat.chat.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.litedoid.orachat.R;
import com.litedoid.orachat.chat.ChatBaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

@EActivity(R.layout.activity_main)
public class ChatDetailsActivity extends ChatBaseActivity
{
    private static final String TAG = ChatDetailsActivity.class.getSimpleName();

    ChatDetailsFragment chatDetailsFragment;
    ChatDetailsPresenter chatDetailsPresenter;

    @Extra
    String chatName;

    @Extra
    int chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        currentView = ChatScreenView.CHAT_DETAILS;

        initChatDetailsFragment();
    }

    @AfterViews
    void initViews()
    {
        Log.d(TAG, "initViews");

        menuTitle.setText(chatName);

        showCurrentView();
        setNavigationOptions();
    }

    private void initChatDetailsFragment()
    {
        chatDetailsFragment = ChatDetailsFragment_.newInstance();
        chatDetailsPresenter = new ChatDetailsPresenter(chatDetailsFragment);
        chatDetailsFragment.setPresenter(chatDetailsPresenter);
        chatDetailsFragment.chatId = chatId;
    }

    private void showCurrentView()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout, chatDetailsFragment);
        fragmentTransaction.commit();
    }


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
