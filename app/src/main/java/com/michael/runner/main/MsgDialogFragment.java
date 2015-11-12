package com.michael.runner.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.michael.runner.R;



public class MsgDialogFragment extends DialogFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "MsgDialogFragment";
    private static final String MSG = "msg";
    private static final String TITLE = "title";

    private String mMsg;
    private String mTitle;

    private FrameLayout mDialogLayout;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MsgDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MsgDialogFragment newInstance(String msg, String title) {
        MsgDialogFragment fragment = new MsgDialogFragment();
        Bundle args = new Bundle();
        args.putString(MSG, msg);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public MsgDialogFragment() {
        // Required empty public constructor
        //super(R.style.DialogAnimation);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMsg = getArguments().getString(MSG);
            mTitle = getArguments().getString(TITLE);
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_message_dialog, null);


       // dialog.getWindow().getAttributes().windowAnimations = R.anim.bounce_up;

        TextView msgView =  (TextView)view.findViewById(R.id.msg);
        msgView.setText(mMsg);

        TextView titleView =  (TextView) view.findViewById(R.id.title);
        titleView.setText(mTitle);


        mDialogLayout =  (FrameLayout) view.findViewById(R.id.dialog_layout);
        mDialogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //YoYo.with(Techniques.Bounce).p
//                if (!YoYo.with(Techniques.BounceIn)
//                        .duration(700)
//                        .playOn(mDialogLayout).()) {

                dismiss();
//                }
            }
        });


        builder.setView(view);

        Dialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        return dialog;
    }


}
