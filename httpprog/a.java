import javax.servlet.http.HttpServletRequest;

class a {
public static void main(String args[])
{
HttpServletRequest request;
Map<String, String> getHeadersInfo() 
{

Map<String, String> map = new HashMap<String, String>();
Enumeration headerNames = request.getHeaderNames();

while (headerNames.hasMoreElements()) 
{
	String key = (String) headerNames.nextElement();
	String value = request.getHeader(key);
	map.put(key, value);
}

return map;
}

}
}