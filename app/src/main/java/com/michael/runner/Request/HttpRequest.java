package com.michael.runner.request;

import android.util.Log;

import com.goebl.david.Webb;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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

    public HttpResponse makePostRequest(String url) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        return httpclient.execute(request);
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
        Webb webb = Webb.create();
    }
}
