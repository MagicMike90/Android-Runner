package com.michael.runner.utils;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

import com.michael.runner.R;

/**
 * Created by michael on 13/11/2015.
 */
public class MsgDialogController {
    private static MsgDialogController ourInstance;

    private Context mCtx;
    private String mMsg;
    private ProgressDialog mProgressDialog;


    public static MsgDialogController getInstance(Context ctx, String msg) {
        ourInstance = new MsgDialogController(ctx, msg);

        return ourInstance;
    }

    private MsgDialogController(Context ctx, String msg) {
        mCtx = ctx;
        mMsg = msg;

        mProgressDialog = new ProgressDialog(ctx);
        mProgressDialog.setMessage("Registering");
        mProgressDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        mProgressDialog.setCancelable(false);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (show) mProgressDialog.show();
        else mProgressDialog.dismiss();
    }

}
