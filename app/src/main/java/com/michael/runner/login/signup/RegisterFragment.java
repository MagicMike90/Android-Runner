package com.michael.runner.login.signup;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.michael.runner.R;
import com.michael.runner.main.Constans;
import com.michael.runner.main.MsgDialogFragment;
import com.michael.runner.main.RunnerActivity;
import com.michael.runner.utils.SessionManager;
import com.michael.runner.utils.VolleyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RegisterFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "RegisterFragment";

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;

    private ProgressDialog mProgressDialog;
    //private MsgDialogFragment mMsgDialog;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SigninFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    public RegisterFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);
        //populateAutoComplete();

        mPasswordView = (EditText) view.findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        mLoginFormView = view.findViewById(R.id.login_form);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Registering");
        mProgressDialog.setCancelable(false);

        return view;
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegister() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        String error = "";
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            // mPasswordView.setError(getString(R.string.error_invalid_password));
            error = getString(R.string.error_invalid_password);

            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            // mEmailView.setError(getString(R.string.error_field_required));
            error = getString(R.string.error_field_required);
            //showMsgDialog(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            // mEmailView.setError(getString(R.string.error_invalid_email));
            error = getString(R.string.error_invalid_email);
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.

            showMsgDialog(error, "Error");
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
//            mAuthTask = new UserLoginTask(email, password);

//            mAuthTask.execute((Void) null);
            RegisterTask(email, password);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic


//        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
//                .matches();
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (show) mProgressDialog.show();
        else mProgressDialog.dismiss();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(),
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    private void RegisterTask(final String username, final String password) {
        showProgress(true);

        JSONObject body = new JSONObject();
        try {
            body.put("username", username);
            body.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Constans.SIGN_UP_URL, body, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean success = response.getBoolean("success");
                    if (success) {
                        //store the user info in shareperference
                        SessionManager sm = SessionManager.getInstance(getActivity());
                        sm.createLoginSession(username,password);
                        showProgress(false);
                        transitToActivity(RunnerActivity.class,username);
                    } else {
                        JSONObject extrasJSON = response.getJSONObject("extras");
                        String msg = extrasJSON.getString("msg");
                        showMsgDialog(msg, "Error");
                    }

                } catch (JSONException e) {
                    showProgress(false);
                    e.printStackTrace();
                }

                //showProgress(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

                //showProgress(false);
            }
        });
//        StringRequest sr = new StringRequest(Request.Method.POST, Constans.SIGN_UP_URL,new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //mPostCommentResponse.requestCompleted();
//                Log.d(TAG,response);
//               // Toast.makeText(getActivity(),REQUEST_READ_CONTACTS,Toast.LENGTH_LONG).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//               // mPostCommentResponse.requestEndedWithError(error);
//                Log.d(TAG,error.getMessage());
//            }
//        });


        // Adding request to request queue
        VolleyManager.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    private void showMsgDialog(String msg, String title) {
        MsgDialogFragment dialog = MsgDialogFragment.newInstance(msg, title);

        dialog.show(getActivity().getFragmentManager(), TAG);
    }

    public void transitToActivity(Class<?> cls, String username) {
        Intent i = new Intent(getActivity(), cls);
        Log.d(TAG,username);
        i.putExtra("username",username);
        startActivity(i);
        //getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
