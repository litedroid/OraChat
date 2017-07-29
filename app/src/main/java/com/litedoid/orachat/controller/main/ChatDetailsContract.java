package com.litedoid.orachat.controller.main;


import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;

public interface ChatDetailsContract
{
    interface View extends BaseView<Presenter>
    {
        void showChatDetails(ChatListResult.Chat chat);

        void addChatMessage();
    }

    interface Presenter extends BasePresenter
    {
        void loadChatDetails(int chatId);
    }
}
