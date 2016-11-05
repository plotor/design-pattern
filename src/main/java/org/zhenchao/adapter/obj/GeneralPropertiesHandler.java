package org.zhenchao.adapter.obj;

import java.util.Properties;

/**
 * 一般properties文件处理类
 *
 * @author zhenchao.wang 2016-11-05 11:13
 * @version 1.0.0
 */
public class GeneralPropertiesHandler implements AbstractPropertiesHandler {

    public Properties loadProperties(String filepath) {
        // ... 文件加载逻辑
        return new Properties();
    }

    public boolean saveProperties(Properties properties, String filepath) {
        // ... 文件存储逻辑
        return true;
    }

}
