package com.litedoid.orachat.controller.main;

import android.util.Log;

import com.google.gson.Gson;
import com.litedoid.orachat.Constants;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.APIErrorType;
import com.litedoid.orachat.api.client.OraChatAPIClient;
import com.litedoid.orachat.api.model.ChatListResult;

public class ChatListPresenter implements ChatListContract.Presenter
{
    private static final String TAG = ChatListPresenter.class.getSimpleName();

    private ChatListContract.View chatListView;

    public ChatListPresenter(ChatListContract.View view)
    {
        chatListView = view;
        chatListView.setPresenter(this);
    }

    @Override
    public void start()
    {
        Log.d(TAG, "start");
    }

    @Override
    public void loadChatContent()
    {
        new OraChatAPIClient().getChatList(1, Constants.PAGE_LIMIT, new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "getChatList Error: " + apiErrorType.name());
            }

            @Override
            public void onSuccess(Object o)
            {
                ChatListResult result = (ChatListResult) o;

                Log.d(TAG, "getChatList Success: " + new Gson().toJson(o));

                chatListView.showChats(result.getChats());
            }
        });


    }
}
