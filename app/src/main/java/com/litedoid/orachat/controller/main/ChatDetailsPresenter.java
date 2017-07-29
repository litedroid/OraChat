package com.litedoid.orachat.controller.main;

import android.util.Log;

import com.google.gson.Gson;
import com.litedoid.orachat.Constants;
import com.litedoid.orachat.api.APICallback;
import com.litedoid.orachat.api.APIErrorType;
import com.litedoid.orachat.api.client.OraChatAPIClient;
import com.litedoid.orachat.api.model.ChatMessagesResult;

public class ChatDetailsPresenter implements ChatDetailsContract.Presenter
{
    private static final String TAG = ChatDetailsPresenter.class.getSimpleName();

    private ChatDetailsContract.View chatListView;

    public ChatDetailsPresenter(ChatDetailsContract.View view)
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
    public void loadChatDetails(int chatId)
    {
        new OraChatAPIClient().getChatMessages(chatId, 1, Constants.PAGE_LIMIT, new APICallback()
        {
            @Override
            public void onError(APIErrorType apiErrorType)
            {
                Log.d(TAG, "loadChatDetailsContent Error: " + apiErrorType.name());
            }

            @Override
            public void onSuccess(Object o)
            {
                ChatMessagesResult result = (ChatMessagesResult) o;

                Log.d(TAG, "loadChatDetails Success: " + new Gson().toJson(o));

                chatListView.showChatDetails(result.getChatMessages());
            }
        });


    }
}
