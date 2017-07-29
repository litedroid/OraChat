package com.litedoid.orachat.helpers;


import android.content.Context;

import com.litedoid.orachat.R;
import com.litedoid.orachat.api.model.ChatListResult;
import com.litedoid.orachat.api.model.User;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import java.util.List;

import static org.joda.time.Minutes.minutesBetween;

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

        if (!DateHelper.isSameDate(chatDate, DateTime.now()))
        {
            chatDateString = DateHelper.getShortDate(chatDate);
        }

        return chatDateString;
    }

    public static String getTimeSince(Context context, String createDate)
    {
        DateTime messageDate = DateHelper.serverValueToDate(createDate);

        DateTime now = DateTime.now();

        if (minutesBetween(messageDate, now).getMinutes() < 60)
            return "" + minutesBetween(messageDate, now).getMinutes() + " " + context.getString(R.string.mins_ago);
        else if (Days.daysBetween(messageDate, now).getDays() < 1)
            return "" + Days.daysBetween(messageDate, now).getDays() + " " + context.getString(R.string.hours_ago);
        else if (Months.monthsBetween(messageDate, now).getMonths() < 1)
            return "" + Days.daysBetween(messageDate, now).getDays() + " " + context.getString(R.string.days_ago);
        else
            return "" + Months.monthsBetween(messageDate, now).getMonths() + " " + context.getString(R.string.months_ago);
    }
}
