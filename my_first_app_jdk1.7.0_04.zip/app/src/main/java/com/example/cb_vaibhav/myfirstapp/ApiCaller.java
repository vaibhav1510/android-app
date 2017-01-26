package com.example.cb_vaibhav.myfirstapp;

import com.example.cb_vaibhav.myfirstapp.http.HttpConfig;
import com.example.cb_vaibhav.myfirstapp.http.HttpUtil;
import com.example.cb_vaibhav.myfirstapp.http.Params;

/**
 * Created by cb-vaibhav on 26/01/17.
 */

public class ApiCaller {
    private String userName;
    private String passwd;

    public ApiCaller(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
    }

    public String request(String url) throws Exception {
        HttpConfig config = new HttpConfig(userName, passwd);
        HttpUtil http = new HttpUtil(config);
        HttpUtil.Response resp = http.get(url, new Params());
        System.out.println(resp.jsonContent().toString(2));
        return resp.jsonContent().toString(2);
    }

}
