package com.michael.runner.login;

import android.support.v4.app.Fragment;

import com.michael.main.BaseFragmentActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }
}

