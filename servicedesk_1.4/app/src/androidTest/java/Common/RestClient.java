package Common; /**
 * Created by Admin-PC on 2/1/2017.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class RestClient {
 // To get CSRF-Token
    public static String getcsrftoken(String Domain){
        String output="";
        try {
            URL url= new URL(Domain+ "/getcsrftoken") ;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            // Get Output from Server
            while ((output = br.readLine()) != null) {
                output += output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     return output;
    }
    //To get authorization token
   public static String gettoken(String Domain, Map<String, String> para){
       String output="";
    try
    {

        URL url = new URL(Domain + "/api-token-auth/");

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,String> param : para.entrySet())
        {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

       // System.out.println("Output from Server .... \n");
        while ((output = in.readLine()) != null)
        {
            output += output;
        }
   } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
       return output;
   }

    // post username, password and url
    public static String postrequest(String Domain, String path, Map<String, String> para ) {
        StringBuffer result = new StringBuffer();
     try
     {
         URL url = new URL(Domain + "/" + path + "/" );
         StringBuilder postData = new StringBuilder();
         for (Map.Entry<String,String> param : para.entrySet())
         {
             if (postData.length() != 0) postData.append('&');
             postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
             postData.append('=');
             postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
         }

        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));


        String line = "";
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        JSONObject o = new JSONObject(result.toString());
        String s1=o.get("token").toString();
        System.out.print(s1);

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
         e.printStackTrace();
     }
        return String.valueOf(result);
    }

}




