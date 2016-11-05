package org.zhenchao.adapter.obj;

import java.util.Properties;

/**
 * 抽象properties文件操作类
 *
 * @author zhenchao.wang 2016-11-05 11:00
 * @version 1.0.0
 */
public interface AbstractPropertiesHandler {

    /**
     * 加载properties
     *
     * @param filepath
     * @return
     */
    Properties loadProperties(String filepath);

    /**
     * 存储properties
     *
     * @param properties
     * @param filepath
     * @return
     */
    boolean saveProperties(Properties properties, String filepath);

}
