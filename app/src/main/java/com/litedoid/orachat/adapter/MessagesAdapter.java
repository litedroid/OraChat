package com.litedoid.orachat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.litedoid.orachat.R;
import com.litedoid.orachat.api.model.ChatMessage;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder>
{
    private static final String TAG = MessagesAdapter.class.getSimpleName();

    private Context context;

    private static final int TYPE_LEFT = 1;
    private static final int TYPE_RIGHT = 2;

    private List<ChatMessage> chatMessages;

    public MessagesAdapter(Context context, List<ChatMessage> messages)
    {
        this.context = context;
        this.chatMessages = messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder");

        View view;

        if (viewType == TYPE_LEFT)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_message_left, parent, false);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_message_right, parent, false);
        }

        return new MessageViewHolder(view);
    }


    @Override
    public int getItemViewType(int position)
    {
        int viewType = TYPE_LEFT;

        if (position % 2 == 0)
            viewType = TYPE_RIGHT;

        return viewType;
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, final int position)
    {
        Log.d(TAG, "onBindViewHolder");

        final ChatMessage message = chatMessages.get(position);


        Log.d(TAG, "onBindViewHolder message: " + new Gson().toJson(message));

        holder.messageTextView.setText(message.getMessage());
        holder.authorTextView.setText(message.getCreatedAt());

    }

    @Override
    public int getItemCount()
    {
        return chatMessages.size();
    }

}
