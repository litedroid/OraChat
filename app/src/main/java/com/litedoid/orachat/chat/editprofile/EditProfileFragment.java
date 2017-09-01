package com.litedoid.orachat.chat.editprofile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.litedoid.orachat.R;
import com.litedoid.orachat.api.model.User;
import com.litedoid.orachat.helpers.DialogHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_register)
public class EditProfileFragment extends Fragment implements EditProfileContract.View
{
    private static final String TAG = EditProfileFragment.class.getSimpleName();

    private EditProfileContract.Presenter presenter;

    @ViewById(R.id.nameEditText)
    EditText nameEditText;

    @ViewById(R.id.emailEditText)
    EditText emailEditText;

    @ViewById(R.id.passwordEditText)
    EditText passwordEditText;

    @ViewById(R.id.confirmEditText)
    EditText confirmEditText;

    @ViewById(R.id.logout_button)
    Button logoutButton;

    @ViewById(R.id.register_button)
    Button saveButton;


    public static EditProfileFragment newInstance()
    {
        return new EditProfileFragment_();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews()
    {
        Log.d(TAG, "afterViews");

        saveButton.setText(R.string.save);

        logoutButton.setVisibility(View.VISIBLE);

        presenter.loadProfile();
    }


    @Override
    public void setPresenter(EditProfileContract.Presenter presenter)
    {
        Log.d(TAG, "setPresenter");
        this.presenter = presenter;
    }

    @Override
    public void showProfile(User user)
    {
        Log.d(TAG, "showProfile: " + user.getName());

        nameEditText.setText(user.getName());
        emailEditText.setText(user.getEmail());
    }

    @Click(R.id.register_button)
    public void onClickSaveProfile()
    {
        Log.d(TAG, "onClickSaveProfile");
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirm = confirmEditText.getText().toString().trim();

        presenter.saveProfile(name, email, password, confirm);
    }

    @Override
    public void showSaveProfileSuccess()
    {
        Log.d(TAG, "showSaveProfileSuccess");

        DialogHelper.showOKDialog(getActivity(), R.string.update_user_success_title, R.string.update_user_success_message);
    }

    @Override
    public void showSaveProfileFailure()
    {
        Log.d(TAG, "showSaveProfileFailure");

        DialogHelper.showOKDialog(getActivity(), R.string.update_user_error_title, R.string.update_user_error_message);
    }

    @Click(R.id.logout_button)
    protected void onClickLogout()
    {
        Log.d(TAG, "onClickLogout");

        new android.app.AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.logout_confirmation_title))
                .setMessage(getString(R.string.logout_confirmation_message))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        presenter.logout(getActivity());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}