import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.Object.*;
import java.util.*;  

public class testurl{

 void Hit() {

	  try {
        

		URL url = new URL("http://192.168.50.216:8000/getcsrftoken");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
                      
		String username ="tripti@dmx.com";
                String pass = "123456789";
                
		OutputStream os = conn.getOutputStream();

		os.write(username.getBytes());
                os.write(pass.getBytes());
              
		os.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
             }

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	 }
}
	
HttpServletRequest request;

Map<String, String> getHeadersInfo() 
{

  Map<String, String> map = new HashMap<String, String>();

  Enumeration headerNames = request.getHeaderNames();
  while (headerNames.hasMoreElements()) {
	                                 String key = (String) headerNames.nextElement();
	                                 String value = request.getHeader(key);
	                                 map.put(key, value);
                                        }

  return map;
  }

public static void main(String[] args){

testurl o1=new testurl();
o1.Hit();
o1.getHeadersInfo();

}
}