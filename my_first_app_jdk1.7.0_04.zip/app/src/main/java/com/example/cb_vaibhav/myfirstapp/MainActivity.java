package com.example.cb_vaibhav.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String agentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiCaller caller = new ApiCaller("har@gmail.com", "pasword");
        try {
            String jsonStr = caller.request("www.dmxtech.com/service_desk");
            JSONObject jsonObj = new JSONObject(jsonStr);
            this.agentName = jsonObj.getString("agentName");
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.agentName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
