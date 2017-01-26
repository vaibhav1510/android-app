package com.example.cb_vaibhav.myfirstapp.http;

import android.util.Base64;

import com.example.cb_vaibhav.myfirstapp.http.HttpConfig;

import java.io.*;
import java.net.*;
import java.util.StringJoiner;
import java.util.zip.GZIPInputStream;

import org.json.*;

import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    public enum Method {

        GET, POST, PUT, DELETE;

    }

    /**
     * To temporarily capture the http response
     */
    public static class Response {

        public final int httpCode;

        public final Map<String, String> headers;

        public final String content;

        private Response(int httpCode, Map<String, String> headers, String content) {
            this.httpCode = httpCode;
            this.headers = headers;
            this.content = content;
        }

        public boolean isSuccess() {
            return httpCode > 199 && httpCode < 300; // 20x codes
        }

        public boolean is40x() {
            return httpCode > 399 && httpCode < 500;
        }

        public boolean is50x() {
            return httpCode > 499;
        }

        public JSONObject jsonContent() {
            JSONObject obj;
            try {
                obj = new JSONObject(content);
            } catch (JSONException exp) {
                throw new RuntimeException(content);
            }
            checkRequiredJSONResp(obj);
            return obj;
        }

        public JSONArray jsonArrayContent() {
            JSONArray arr;
            try {
                arr = _jsonArrayContent();
            } catch (JSONException exp) {
                throw new RuntimeException(content);
            }
            return arr;
        }

        public JSONArray _jsonArrayContent() throws JSONException {
            return new JSONArray(content);
        }
    }

    private HttpConfig httpConfig;

    public HttpUtil(HttpConfig config) {
        this.httpConfig = config;
    }

    public Response get(String url, Params params) throws IOException {
        return get(url, params, null);
    }

    public Response get(String url, Params params, Map<String, String> extraHeaders) throws IOException {
        return get(url, params, extraHeaders, true);
    }

    public Response get(String url, Params params, Map<String, String> extraHeaders, boolean throwIfEmptyResponse) throws IOException {

        String queryStr = params == null ? null : JspUtils.toQueryStr(params.map());
        if (queryStr != null && !queryStr.isEmpty()) {
            url = url + '?' + queryStr;
        }
        HttpURLConnection conn = createConnection(url, Method.GET, extraHeaders);
        Response resp = sendRequest(conn, throwIfEmptyResponse);
        return resp;
    }

    public Response post(String url, Params params) throws IOException {
        return post(url, params, null);
    }

    public Response post(String url, Params params, Map<String, String> extraHeaders) throws IOException {
        return post(url, params == null ? null : JspUtils.toQueryStr(params.map()), extraHeaders);
    }

    public Response post(String url, String content) throws IOException {
        return post(url, content, null);
    }

    public Response post(String url, String content, Map<String, String> extraHeaders) throws IOException {
        return doFormSubmit(url, Method.POST, content, extraHeaders);
    }

    public Response put(String url, Params params, Map<String, String> extraHeaders) throws IOException {
        return put(url, JspUtils.toQueryStr(params.map()), extraHeaders);
    }

    public Response put(String url, String content) throws IOException {
        return doFormSubmit(url, Method.PUT, content, null);
    }

    public Response put(String url, String content, Map<String, String> extraHeaders) throws IOException {
        return doFormSubmit(url, Method.PUT, content, extraHeaders);
    }

    private Response doFormSubmit(String url, Method m, String queryStr, Map<String, String> extraHeaders) throws IOException {
        HttpURLConnection conn = createConnection(url, m, extraHeaders);
        writeContent(conn, queryStr);
        Response resp = sendRequest(conn, true);
        /*if(resp.httpCode > 299){
            throw new RuntimeException(resp.jsonContent.toString(4));
        }*/
        return resp;
    }

    public static String toQueryStr(Map<String, String> map) throws Exception{
        StringJoiner buf = new StringJoiner("&");
        for(Map.Entry<String, String> entry : map.entrySet()){
            String keyValPair = escQuery(entry.getKey()) + "=" + escQuery(entry.getValue());
            buf.add(keyValPair);
        }
        return buf.toString();
    }

    public static String escQuery(String queryComp) throws Exception{
        return URLEncoder.encode(queryComp, "UTF-8");
    }

    private static void writeContent(HttpURLConnection conn, String queryStr)
            throws IOException {
        if (queryStr == null) {
            return;
        }
        OutputStream os = conn.getOutputStream();
        try {
            os.write(queryStr.getBytes(Env.encoding()));
        } finally {
            os.close();
        }
    }

    private HttpURLConnection createConnection(String url, Method m, Map<String, String> extraHeaders) throws IOException {

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod(m.name());
        setTimeouts(conn, httpConfig);
        addHeaders(conn, httpConfig, extraHeaders);
        if (m == Method.POST || m == Method.PUT) {
            addHeader(conn, "Content-Type", httpConfig.contentType + ";charset=" + Env.encoding());
            conn.setDoOutput(true);
        }
        conn.setUseCaches(false);
        return conn;
    }

    private static Response sendRequest(HttpURLConnection conn, boolean throwIfEmptyResponse) throws IOException {
        long time = System.currentTimeMillis();
        int httpRespCode;
        try {
            httpRespCode = conn.getResponseCode();
        } finally {
            KVL.callTime("httputil", time);
        }
        if (httpRespCode == HttpURLConnection.HTTP_NO_CONTENT) {
            throw new RuntimeException("Got http_no_content response");
        }
        boolean error = httpRespCode < 200 || httpRespCode > 299;
        return new Response(httpRespCode, getRespHeaders(conn), getContentAsString(conn, error, throwIfEmptyResponse));
    }

    private static void setTimeouts(URLConnection conn, HttpConfig config) {
        conn.setConnectTimeout(config.connectTimeout);
        conn.setReadTimeout(config.readTimeout);
    }

    private static void addHeaders(HttpURLConnection conn, HttpConfig config, Map<String, String> extraHeaders) {
        addHeader(conn, "Accept-Charset", Env.encoding());
        if (config.username != null) {
            addHeader(conn, "Authorization", getAuthValue(config));
        }
        addHeader(conn, "Accept", config.respType);
        if (extraHeaders != null && !extraHeaders.isEmpty()) {
            for (Map.Entry<String, String> hdr : extraHeaders.entrySet()) {
                addHeader(conn, hdr.getKey(), hdr.getValue());
            }
        }
    }

    private static void addHeader(HttpURLConnection conn, String headerName,
                                  String value) {
        conn.setRequestProperty(headerName, value);
    }

    private static String getAuthValue(HttpConfig config) {
        return "Basic " + Base64.encodeToString((config.username + ":" + config.password).getBytes(), Base64.DEFAULT)
                .replaceAll("\r?", "").replaceAll("\n?", "");
    }


    private static Map<String, String> getRespHeaders(HttpURLConnection conn) {
        Map<String, String> toRet = new HashMap<String, String>();
        for (int i = 0; ; i++) {
            String key = conn.getHeaderFieldKey(i);
            String val = conn.getHeaderField(i);
            if (key == null && val == null) { // no more headers
                break;
            }
            if (key != null) {
                toRet.put(key, val);
            }
        }
        return toRet;
    }

    private static String getContentAsString(HttpURLConnection conn,
                                             boolean error, boolean throwIfEmptyResponse) throws IOException {
        if (error) {
            //throw GlobalUtil.rtExp("");
        }
        InputStream resp = (error) ? conn.getErrorStream() : conn.getInputStream();
        if (resp == null) {
            if (throwIfEmptyResponse)
                throw new RuntimeException("Got Empty Response ");
            else
                return "Got Empty Response";

        }
        try {
            if ("gzip".equalsIgnoreCase(conn.getContentEncoding())) {
                resp = new GZIPInputStream(resp);
            }
            InputStreamReader inp = new InputStreamReader(resp, Env.encoding());
            StringBuilder buf = new StringBuilder();
            char[] buffer = new char[1024];//Should use content length.
            int bytesRead;
            while ((bytesRead = inp.read(buffer, 0, buffer.length)) >= 0) {
                buf.append(buffer, 0, bytesRead);
            }
            String content = buf.toString();
            return content;
        } finally {
            resp.close();
        }
    }

    private static void checkRequiredJSONResp(JSONObject respObj) {
        if (respObj == null) {
            throw new RuntimeException(
                    "Expected json formatted content in response");
        }
    }

}

