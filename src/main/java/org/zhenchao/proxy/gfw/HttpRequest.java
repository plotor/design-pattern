package org.zhenchao.proxy.gfw;

/**
 * HTTP请求抽象
 *
 * @author zhenchao.wang 2016-12-11 20:29
 * @version 1.0.0
 */
public interface HttpRequest {

    String get(String url) throws Exception;

}
