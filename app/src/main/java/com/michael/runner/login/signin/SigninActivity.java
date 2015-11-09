package com.michael.runner.login.signin;

import android.support.v4.app.Fragment;

import com.michael.runner.main.BaseFragmentActivity;


/**
 * A login screen that offers login via email/password.
 */
public class SigninActivity extends BaseFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return SigninFragment.newInstance();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}

