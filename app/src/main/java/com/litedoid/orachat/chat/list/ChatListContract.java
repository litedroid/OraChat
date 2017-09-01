package com.litedoid.orachat.chat.list;


import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;

import java.util.List;

public interface ChatListContract
{
    interface View extends BaseView<Presenter>
    {
        void showChats(List<ChatListResult.Chat> chats);
    }

    interface Presenter extends BasePresenter
    {
        void loadChatContent();
    }
}
