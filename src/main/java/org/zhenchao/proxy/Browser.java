package org.zhenchao.proxy;

/**
 * 简单浏览器
 *
 * @author zhenchao.wang 2016-12-11 21:01
 * @version 1.0.0
 */
public class Browser {

    public static void main(String[] args) {

        String url = "https://www.google.com";

        // 不基于代理，直接访问
        HttpRequest httpRequest = new HttpRequestImpl();
        try {
            System.out.println(httpRequest.get(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 基于代理的访问
        httpRequest = new HttpRequestProxy(httpRequest);
        try {
            System.out.println(httpRequest.get(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
