package com.michael.runner.utils;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

/**
 * Created by michael on 13/11/2015.
 */
public class ProgressDialogController {
    private static ProgressDialogController ourInstance;

    private Context mCtx;
    private String mMsg;
    private ProgressDialog mProgressDialog;


    public static ProgressDialogController getInstance(Context ctx, String msg) {
        ourInstance = new ProgressDialogController(ctx, msg);

        return ourInstance;
    }

    private ProgressDialogController(Context ctx, String msg) {
        mCtx = ctx;
        mMsg = msg;

        mProgressDialog = new ProgressDialog(ctx);
        mProgressDialog.setMessage(msg);
        //mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        mProgressDialog.setCancelable(false);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (show) mProgressDialog.show();
        else mProgressDialog.dismiss();
    }

}
