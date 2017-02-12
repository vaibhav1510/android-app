package com.example.cb_vaibhav.myfirstapp.dmxtest;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostUrl2 {

    public static void main(String[] args) throws Exception {
        String csrfToken = getCsrfToken();
        String data = authenticate(csrfToken);
    }

    public static String getCsrfToken() throws Exception {
        try {
            URL url = new URL("http://192.168.50.216:8000/getcsrftoken");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            String value = conn.getHeaderField(output);
            conn.disconnect();
            return value;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String authenticate(String csrfToken) throws Exception {
        try {
            URL url = new URL("http://192.168.50.216:8000/authenticate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");

            //csrfToken in header
            conn.setRequestProperty("X-CSRF-TOKEN", csrfToken);

//            //csrfToken as BASIC OAuth
//            conn.setRequestProperty("Authorization", "BASIC " + csrfToken + ":");

//            //csrfToken as BASIC OAuth where csrfToken is encoded
//            String apiKey = Base64.encodeToString((csrfToken + ":").getBytes(), Base64.DEFAULT)
//                    .replaceAll("\r?", "").replaceAll("\n?", "");
//            conn.setRequestProperty("Authorization", "BASIC " + apiKey);

            //conn.setRequestProperty("username", "tripti@dmc.com");
            //conn.setRequestProperty("password", "123456789");
            String userName = "tripti@dmc.com";
            String passwd = "123456789";
            String userNamePasswd = "Basic " + Base64.encodeToString((userName + ":" + passwd).getBytes(), Base64.DEFAULT)
                    .replaceAll("\r?", "").replaceAll("\n?", "");
            conn.setRequestProperty("Authorization", userNamePasswd);


            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                output += output;
            }
            String value = conn.getHeaderField(output);
            conn.disconnect();
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
