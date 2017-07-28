package com.litedoid.orachat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.litedoid.orachat.R;


public class ChatViewHolder extends RecyclerView.ViewHolder
{
    public LinearLayout chatLayout;
    public TextView topDateTextView;
    public TextView chatNameTextView;
    public TextView creatorTextView;
    public TextView lastMessageTextView;

    public ChatViewHolder(View v)
    {
        super(v);

        this.chatLayout = (LinearLayout) v.findViewById(R.id.chat_row_layout);
        this.topDateTextView = (TextView) v.findViewById(R.id.chat_top_date_textview);
        this.chatNameTextView = (TextView) v.findViewById(R.id.chat_name_textview);
        this.creatorTextView = (TextView) v.findViewById(R.id.chat_creator_textview);
        this.lastMessageTextView = (TextView) v.findViewById(R.id.chat_last_message_textview);

    }
}