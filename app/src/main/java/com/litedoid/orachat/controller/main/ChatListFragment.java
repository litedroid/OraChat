package com.litedoid.orachat.controller.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.litedoid.orachat.R;
import com.litedoid.orachat.adapter.ChatsAdapter;
import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.interfaces.ChatListListener;
import com.litedoid.orachat.interfaces.MainNavigationListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EFragment(R.layout.fragment_chatlist)
public class ChatListFragment extends Fragment implements ChatListContract.View, ChatListListener
{
    private static final String TAG = ChatListFragment.class.getSimpleName();

    private ChatListContract.Presenter presenter;

    private MainNavigationListener mainNavigationListener;

    @ViewById(R.id.chat_recycler_view)
    protected RecyclerView chatRecyclerView;

    private List<ChatListResult.Chat> chats = new ArrayList<>();

    private ChatsAdapter chatsAdapter;

    public static ChatListFragment newInstance()
    {
        return new ChatListFragment_();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews()
    {
        Log.d(TAG, "afterViews");

        presenter.start();

        chatRecyclerView.setHasFixedSize(false);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatRecyclerView.setItemAnimator(null);

        chatsAdapter = new ChatsAdapter(getContext(), chats, this);
        chatRecyclerView.setAdapter(chatsAdapter);

        presenter.loadChatContent();
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

    @Override
    public void setPresenter(ChatListContract.Presenter presenter)
    {
        Log.d(TAG, "setPresenter");
        this.presenter = presenter;
    }

    @UiThread
    @Override
    public void showChats(List<ChatListResult.Chat> chats)
    {
        Log.d(TAG, "showChats count: " + chats.size());

        this.chats.clear();

        this.chats.addAll(chats);
        chatsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setMainNavigationListener(MainNavigationListener mainNavigationListener)
    {
        this.mainNavigationListener = mainNavigationListener;
    }

    @Override
    public void onSelectChat(int chatId)
    {
        Log.d(TAG, "onSelectChat: " + chatId);

        mainNavigationListener.onLoadChatDetails(chatId);
    }
}