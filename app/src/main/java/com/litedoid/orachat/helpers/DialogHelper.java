package com.litedoid.orachat.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;

import com.litedoid.orachat.R;

public class DialogHelper
{
    private static final String TAG = DialogHelper.class.getSimpleName();


    public static void showDialogWithButtonText(Context context, int titleResourceId, int messageResourceId, int buttonTextResourceId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(titleResourceId)
                .setMessage(messageResourceId)
                .setPositiveButton(buttonTextResourceId, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.show();

        TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

    public static void showOKDialog(Context context, int titleResourceId, int messageResourceId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(titleResourceId)
                .setMessage(messageResourceId)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.show();

        TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

    public static void showOKDialog(Context context, String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.show();

        TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }
}
