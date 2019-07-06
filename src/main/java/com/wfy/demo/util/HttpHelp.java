package com.wfy.demo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 服务端发送http请求
 */
public class HttpHelp {
    // 发送post请求
    public static String httpPost(String urlStr, String params){
        URL connect;
        StringBuffer data = new StringBuffer();
        try {
            connect = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)connect.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);//post不能使用缓存
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
            paramout.write(params);
            paramout.flush();
            int code = connection.getResponseCode();
            BufferedReader reader=null;
            if(code == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            paramout.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        return data.toString();
    }
}
