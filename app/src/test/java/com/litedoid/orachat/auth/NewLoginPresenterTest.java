package com.litedoid.orachat.auth;

import com.litedoid.orachat.controller.auth.NewLoginPresenter;
import com.litedoid.orachat.controller.auth.NewLoginView;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NewLoginPresenterTest
{
    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void checkIfLoginAttemptsExceeded()
    {
        NewLoginView newLoginView = mock(NewLoginView.class);
        NewLoginPresenter loginPresenter = new NewLoginPresenter(newLoginView);
        Assert.assertEquals(1, loginPresenter.incrementLoginAttempt());
        Assert.assertEquals(2, loginPresenter.incrementLoginAttempt());
        Assert.assertEquals(3, loginPresenter.incrementLoginAttempt());

        Assert.assertTrue(loginPresenter.isLoginAttemptsExceeded());
    }

    @Test
    public void checkIfLoginAttemptsNotExceeded()
    {
        NewLoginView newLoginView = mock(NewLoginView.class);
        NewLoginPresenter loginPresenter = new NewLoginPresenter(newLoginView);

        Assert.assertEquals(1, loginPresenter.incrementLoginAttempt());

        Assert.assertTrue(loginPresenter.isLoginAttemptsNotExceeded());
    }


    @Test
    public void checkLoginSuccess()
    {
        NewLoginView newLoginView = mock(NewLoginView.class);
        NewLoginPresenter loginPresenter = new NewLoginPresenter(newLoginView);

        loginPresenter.login("andy", "password");

        verify(newLoginView).showLoginSuccess();
    }

    @Test
    public void checkLoginFail()
    {
        NewLoginView newLoginView = mock(NewLoginView.class);
        NewLoginPresenter loginPresenter = new NewLoginPresenter(newLoginView);

        loginPresenter.login("andy", "test");

        verify(newLoginView).showErrorMessageForBadCredentials();
    }

    @Test
    public void checkLoginFailForMaxAttempts()
    {
        NewLoginView newLoginView = mock(NewLoginView.class);
        NewLoginPresenter loginPresenter = new NewLoginPresenter(newLoginView);

        loginPresenter.login("andy", "test");
        loginPresenter.login("andy", "test");
        loginPresenter.login("andy", "test");
        loginPresenter.login("andy", "test");

        verify(newLoginView).showErrorMessageForMaxAttempts();
    }

}