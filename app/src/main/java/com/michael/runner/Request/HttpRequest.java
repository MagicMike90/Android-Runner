package com.michael.runner.request;

import android.util.Log;




import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Michael on 11/7/2015.
 */
public class HttpRequest {
    private static String TAG = "HttpRequest";
    private static HttpRequest ourInstance = new HttpRequest();

    public static HttpRequest getInstance() {
        return ourInstance;
    }

    private HttpRequest() {
    }



    public void registerNewUser(String email, String password) {
        try {
            JSONObject mainJObj = new JSONObject();
            mainJObj.put("email", email);
            mainJObj.put("password", password);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void postRequest(String url, JSONObject jb) {

    }
}
