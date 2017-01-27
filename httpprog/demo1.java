import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class demo1 {

   
String username="harshita";
String pass="1234";
URL url="http://192.168.50.216:8000/api-token-auth/";
HttpURLConnection conn =(HttpURLConnection) url.openConnection();

conn.setRequestMethod("POST");
conn.setDoOutput(true);
conn.setDoInput(true);

String username="tripy.dmx.com"
String pass="123456789";


OutputStream out = conn.getOutputStream();

out.write(username.getbyte());
out.write(pass.getbyte());
out.flash();

Bufferedreader br=new Bufferedreader(new InputStreamReader(conn.getInputStream()));

String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) 
                {
			System.out.println(output);
		}

 
conn.disconnect();
}



}