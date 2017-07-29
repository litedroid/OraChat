package com.litedoid.orachat.helpers;


import android.content.Context;

import com.litedoid.orachat.R;
import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.api.model.User;

import org.joda.time.DateTime;

import java.util.List;

public class ChatHelper
{

    public static String getChatUserNames(List<User> users)
    {
        String userNames = "";
        for (User user : users)
        {
            if (userNames.length() == 0)
            {
                userNames = getFirstName(user);
            }
            else
            {
                userNames += ", " + getFirstName(user);
            }

        }

        return userNames;
    }

    public static String getFirstName(User user)
    {
        String firstName = "";

        String[] nameParts = user.getName().split(" ");

        if (nameParts.length > 0)
            firstName = nameParts[0];

        return firstName;
    }


    public static String getChatDate(Context context, ChatListResult.Chat chat)
    {
        String chatDateString = context.getString(R.string.today);

        DateTime chatDate = DateHelper.serverValueToDate(chat.getLastChatMessage().getCreatedAt());

        if(!DateHelper.isSameDate(chatDate, DateTime.now()))
        {
            chatDateString = DateHelper.getShortDate(chatDate);
        }

        return chatDateString;
    }
}
