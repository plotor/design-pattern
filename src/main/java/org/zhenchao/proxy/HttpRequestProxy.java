package org.zhenchao.proxy;

/**
 * HTTP请求代理
 *
 * @author zhenchao.wang 2016-12-11 22:16
 * @version 1.0.0
 */
public class HttpRequestProxy implements HttpRequest {

    private HttpRequest httpRequest;

    public HttpRequestProxy(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public String get(String url) throws Exception {
        // 走Socks5代理
        System.getProperties().put("proxyHost", "127.0.0.1");
        System.getProperties().put("proxyPort", "1080");
        System.getProperties().put("proxySet", "true");
        return this.httpRequest.get(url);
    }

}
