package com.michael.runner.Request;

/**
 * Created by Michael on 11/7/2015.
 */
public class HttpRquest {
    private static HttpRquest ourInstance = new HttpRquest();

    public static HttpRquest getInstance() {
        return ourInstance;
    }

    private HttpRquest() {
    }
}
