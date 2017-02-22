package com.servicedeskmanager.servicedesk.restful;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class RestFulPost extends AsyncTask<Map, Void, String> {
    RestFulResult restFulResult = null;
    ProgressDialog Asycdialog;
    String msg;
    String task;
    RestFulPost(){

    }

    public RestFulPost(RestFulResult restFulResult, Context context, String msg, String task) {
        this.restFulResult = restFulResult;
        this.task=task;
        this.msg = msg;
        Asycdialog = new ProgressDialog(context);
    }

    @Override
    protected String doInBackground(Map... params) {
        String bean =null;
        try {
            Object dataMap = null;
            dataMap = (Object) params[0].get("data");
            String baseUrl = (String) params[0].get("baseurl");
            String path = (String) params[0].get("path");
            String token=(String) params[0].get("token");


            Gson gson = new Gson();

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(baseUrl).path(path);

            bean = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(gson.toJson(dataMap), MediaType.APPLICATION_JSON),
                            String.class);

            Log.i("bean", bean);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.i("Error on RestFulPost:::", e.getMessage());
        }

        return bean;
    }

    @Override
    protected void onPreExecute() {
//        Asycdialog.setMessage(msg);
//        //show dialog
//        Asycdialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        Asycdialog.dismiss();
        restFulResult.onResfulResponse(s,task);
    }


}
