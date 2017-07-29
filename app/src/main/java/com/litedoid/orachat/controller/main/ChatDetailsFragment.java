package com.litedoid.orachat.controller.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.litedoid.orachat.R;
import com.litedoid.orachat.adapter.MessagesAdapter;
import com.litedoid.orachat.api.model.ChatMessage;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

@EFragment(R.layout.fragment_chatdetails)
public class ChatDetailsFragment extends Fragment implements ChatDetailsContract.View
{
    private static final String TAG = ChatDetailsFragment.class.getSimpleName();

    public static ChatDetailsFragment newInstance()
    {
        return new ChatDetailsFragment_();
    }

    private ChatDetailsContract.Presenter presenter;

    private List<ChatMessage> chatMessages = new ArrayList<>();

    private MessagesAdapter messagesAdapter;

    @ViewById(R.id.message_recycler_view)
    protected RecyclerView messageRecyclerView;

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

        messageRecyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        messageRecyclerView.setLayoutManager(linearLayoutManager);
        messageRecyclerView.setItemAnimator(null);

        messagesAdapter = new MessagesAdapter(getContext(), chatMessages);
        messageRecyclerView.setAdapter(messagesAdapter);

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
    public void setPresenter(ChatDetailsContract.Presenter presenter)
    {
        Log.d(TAG, "setPresenter");
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void showChatDetails(List<ChatMessage> chatMessages)
    {
        Log.d(TAG, "showChatDetails: " + chatMessages.size());

        this.chatMessages.addAll(chatMessages);
        messagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void addChatMessage()
    {

    }
}