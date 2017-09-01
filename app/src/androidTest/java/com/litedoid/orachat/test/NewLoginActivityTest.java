package com.litedoid.orachat.test;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.litedoid.orachat.R;
import com.litedoid.orachat.controller.auth.NewLoginActivity_;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NewLoginActivityTest
{
    ActivityTestRule<NewLoginActivity_> activityTestRule = new ActivityTestRule<>(NewLoginActivity_.class);

    @Test
    public void checkUsernameEditTextIsDisplayed()
    {
        activityTestRule.launchActivity((new Intent()));
        onView(withId(R.id.new_username_edittext)).check(matches(isDisplayed()));
    }

//    @Test
//    public void checkErrorMessageIsDisplayedForEmptyData()
//    {
//        activityTestRule.launchActivity((new Intent()));
//        onView(withId(R.id.login_button)).check(matches(isDisplayed())).perform(click());
//        onView(withText("Please check username or password.")).check(matches(isDisplayed()));
//    }

}