package com.example.cb_vaibhav.myfirstapp.http;

/**
 * Created by cb-vaibhav on 26/01/17.
 */

public class HttpConfig {

    public final String username;

    public final String password;

    public final String contentType; // content mime type

    public final String respType; // accept header

    public int connectTimeout;

    public int readTimeout;

    public static final String UTF8 = "UTF-8";

    public HttpConfig(String username) {
        this(username, null);
    }

    public HttpConfig(String username, String password) {
        this(username, password, "application/x-www-form-urlencoded", "application/json;charset=utf-8");
    }

    public HttpConfig(String username, String password, String contentType, String respType) {
        this.username = username;
        this.password = password;
        this.contentType = contentType;
        this.respType = respType;
        this.connectTimeout = 15000;
        this.readTimeout = 60000;
    }

}

