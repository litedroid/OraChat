package com.litedoid.orachat.controller.main;


import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.controller.BasePresenter;
import com.litedoid.orachat.controller.BaseView;
import com.litedoid.orachat.interfaces.MainNavigationListener;

import java.util.List;

public interface ChatListContract
{
    interface View extends BaseView<Presenter>
    {
        void showChats(List<ChatListResult.Chat> chats);

        void setMainNavigationListener(MainNavigationListener mainNavigationListener);
    }

    interface Presenter extends BasePresenter
    {
        void loadChatContent();
    }
}
