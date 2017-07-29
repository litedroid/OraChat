package com.litedoid.orachat.controller.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.litedoid.orachat.R;
import com.litedoid.orachat.api.model.ChatMessage;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

@EFragment(R.layout.fragment_chatlist)
public class ChatDetailsFragment extends Fragment implements ChatDetailsContract.View
{
    private static final String TAG = ChatDetailsFragment.class.getSimpleName();

    public static ChatDetailsFragment newInstance()
    {
        return new ChatDetailsFragment_();
    }

    int chatId = 0;

    private ChatDetailsContract.Presenter presenter;

//    public void setChatId(int chatId)
//    {
//        this.chatId = chatId;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews()
    {
        Log.d(TAG, "afterViews");

        presenter.start();
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

    private void loadChatMessages()
    {
        if(chatId > 0)
            presenter.loadChatDetails(chatId);
    }

    @Override
    public void setPresenter(ChatDetailsContract.Presenter presenter)
    {
        Log.d(TAG, "setPresenter");
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void showChatDetails(List<ChatMessage> chatMessages)
    {

        Log.d(TAG, "showChatDetails: " + chatMessages.size());

    }

    @Override
    public void addChatMessage()
    {

    }
}