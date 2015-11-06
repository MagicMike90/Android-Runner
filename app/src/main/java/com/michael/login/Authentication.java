package com.michael.login;

/**
 * Created by michael on 6/11/2015.
 */
public class Authentication {
    private static Authentication ourInstance = new Authentication();

    public static Authentication getInstance() {
        return ourInstance;
    }

    private Authentication() {
    }
}
