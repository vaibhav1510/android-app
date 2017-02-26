package com.servicedeskmanager.servicedesk.restful;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.ResponseHandler;
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
    ResponseHandler handler;
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
    protected void onPreExecute() {
        //  Asycdialog.setMessage(msg);
        //show dialog
//        Asycdialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Map... params) {
        String bean =null;
        try {
            String baseUrl = (String) params[0].get("baseurl");
            String path = (String) params[0].get("path");

            Log.i("===> URL", baseUrl+path);

            Map<String, String> queryParam = (Map<String, String>)params[0].get("queryparam");
            Gson gson = new Gson();
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(baseUrl).path(path);
            // adding the query param to request.
            for (String key: queryParam.keySet()){
                String value = queryParam.get(key);

                Log.i("===> PARAMS", key+"="+value);
                target = target.queryParam(key, value);  //It is important to know queryParam method won't update current WebTarget object, but return a new one.
            }

        if (isToken) {
                String token = SDUtil.getSession(context, "token");
                bean=target.request(MediaType.APPLICATION_JSON).
                        header(HttpHeaders.AUTHORIZATION,"everestSD "+token).get(String.class);
            }else{
                bean=target.request(MediaType.APPLICATION_JSON).get(String.class);
            }
            Log.i("bean", bean);
            this.handler = ResponseHandler.createSuccessful();
        }
        catch(ProcessingException e){
            e.printStackTrace();
            this.handler = ResponseHandler.createForFail(e);
        }

        Log.i("====>>>>>", bean);
        return bean;
    }



    @Override
    protected void onPostExecute(String s) {
        Asycdialog.dismiss();
        restFulResult.onResfulResponse(s,task, handler);
    }


//
//    method1(){
//        System.out.println("1");
//        method2();
//        System.out.println("3");
//        System.out.println("6");
//        System.out.println("7");
//    }
//
//    method2() implemnt Async {
//        Thread.sleep(200);
//        System.out.println("4");
//        System.out.println("5");
//    }
//
//    main(){
//        method1();
//    }
//
//    //output
//    1 // 200
//    4
//    5
//    3
//
//    1
//    3
//            6
//            7
//    4
//    5
//
//
//    1
//    3
//    6
//    4
//    5
//    7
//
//    1
//    3
//    4
//    6
//    7
//    5
//            1
//    4
//    5
//    3
//    6
//    7

}
