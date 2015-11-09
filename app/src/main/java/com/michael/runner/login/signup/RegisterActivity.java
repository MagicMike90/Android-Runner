package com.michael.runner.login.signup;

import android.support.v4.app.Fragment;

import com.michael.runner.main.BaseFragmentActivity;


public class RegisterActivity extends BaseFragmentActivity
{

    @Override
    protected Fragment createFragment() {
        return RegisterFragment.newInstance();
    }

}
