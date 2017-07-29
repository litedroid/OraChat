package com.litedoid.orachat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.litedoid.orachat.R;
import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.helpers.ChatHelper;
import com.litedoid.orachat.interfaces.ChatListListener;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatViewHolder>
{
    private static final String TAG = ChatsAdapter.class.getSimpleName();

    private Context context;

    private List<ChatListResult.Chat> chats;

    private ChatListListener chatListListener;

    public ChatsAdapter(Context context, List<ChatListResult.Chat> chats, ChatListListener chatListListener)
    {
        this.context = context;
        this.chats = chats;
        this.chatListListener = chatListListener;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, parent, false);

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChatViewHolder holder, final int position)
    {
        Log.d(TAG, "onBindViewHolder");

        final ChatListResult.Chat chat = chats.get(position);


        Log.d(TAG, "onBindViewHolder chat: " + new Gson().toJson(chat));

        holder.topDateTextView.setText(ChatHelper.getChatDate(context, chat));
        holder.chatNameTextView.setText(String.format(context.getString(R.string.chat_row_name), chat.getName(), ChatHelper.getChatUserNames(chat.getUsers())));
        holder.creatorTextView.setText(chat.getLastChatMessage().getUser().getName());
        holder.lastMessageTextView.setText(chat.getLastChatMessage().getMessage());

        holder.chatLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                chatListListener.onSelectChat(chat.getId());
            }
        });

        if(position == chats.size() - 1)
            holder.bottomLine.setVisibility(View.VISIBLE);
        else
            holder.bottomLine.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount()
    {
        return chats.size();
    }

}
