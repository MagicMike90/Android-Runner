package com.michael.runner.login;

import android.support.v4.app.Fragment;

import com.michael.runner.main.BaseFragmentActivity;

public class LoginActivity extends BaseFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }
}
