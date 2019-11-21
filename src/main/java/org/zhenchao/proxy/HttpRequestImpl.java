package org.zhenchao.proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP请求的具体实现
 *
 * @author zhenchao.wang 2016-12-11 20:31
 * @version 1.0.0
 */
public class HttpRequestImpl implements HttpRequest {

    public String get(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        // GET请求
        connection.setRequestMethod("GET");

        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();

        System.out.println("Sending 'GET' request to URL : " + url);

        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        return response.toString();
    }

}
