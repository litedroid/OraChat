package com.litedoid.orachat.controller.auth;


import com.litedoid.orachat.api.model.ChatMessage;
import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;

import java.util.List;

public interface LoginContract
{
    interface View extends BaseView<Presenter>
    {
        void showChatDetails(List<ChatMessage> chatMessages);

        void addChatMessage();
    }

    interface Presenter extends BasePresenter
    {
        void loadChatDetails(int chatId);
    }
}
