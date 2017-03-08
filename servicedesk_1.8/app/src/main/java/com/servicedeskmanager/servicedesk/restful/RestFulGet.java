package com.servicedeskmanager.servicedesk.restful;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.util.SDUtil;

import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

public class RestFulGet extends AsyncTask<Map, Void, String> {
    RestFulResult restFulResult = null;
    ProgressDialog Asycdialog;
    String msg;
    String task;
    Boolean isToken;
    Context context;
    RestFulGet(){

    }

    public RestFulGet(RestFulResult restFulResult, Context context, String msg, String task, Boolean isToken) {
        this.restFulResult = restFulResult;
        this.context=context;
        this.task=task;
        this.msg = msg;
        this.isToken =isToken;
        Asycdialog = new ProgressDialog(context);
    }

    @Override
    protected String doInBackground(Map... params) {
        String bean =null;
        try {
            String baseUrl = (String) params[0].get("baseurl");
            String path = (String) params[0].get("path");
            Map<String, String> queryParam = (Map<String, String>)params[0].get("queryparam");
            Gson gson = new Gson();
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(baseUrl).path(path);
            if (queryParam != null) {
                // adding the query param to request.
                for (String key : queryParam.keySet()) {
                    String value = queryParam.get(key);
                    target = target.queryParam(key, value);  //It is important to know queryParam method won't update current WebTarget object, but return a new one.
                }
            }
        if (isToken) {
                String token = SDUtil.getSession(context, "token");
                bean=target.request(MediaType.APPLICATION_JSON).
                        header(HttpHeaders.AUTHORIZATION,"everestSD "+token).get(String.class);
            }else{
                bean=target.request(MediaType.APPLICATION_JSON).get(String.class);
            }
            Log.i("bean", bean);
        }
        catch(ProcessingException e){
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    protected void onPreExecute() {
        //  Asycdialog.setMessage(msg);
        //show dialog
//        Asycdialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        Asycdialog.dismiss();
        restFulResult.onResfulResponse(s,task);
    }


}
