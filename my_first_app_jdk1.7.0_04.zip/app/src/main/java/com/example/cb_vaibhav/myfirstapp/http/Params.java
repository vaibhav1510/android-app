package com.example.cb_vaibhav.myfirstapp.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cb-vaibhav on 26/01/17.
 */

public class Params {

    Map<String, String> paramList = new HashMap<>();

    public Params put(String key, String val){
        paramList.put(key, val);
        return this;
    }
}
