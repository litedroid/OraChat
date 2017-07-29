package com.litedoid.orachat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.litedoid.orachat.R;

public class MessageViewHolder extends RecyclerView.ViewHolder
{
    public RelativeLayout messageLayout;
    public TextView messageTextView;
    public TextView authorTextView;

    public MessageViewHolder(View v)
    {
        super(v);

        this.messageLayout = (RelativeLayout) v.findViewById(R.id.message_row_layout);
        this.messageTextView = (TextView) v.findViewById(R.id.message_textview);
        this.authorTextView = (TextView) v.findViewById(R.id.message_author_textview);

    }
}