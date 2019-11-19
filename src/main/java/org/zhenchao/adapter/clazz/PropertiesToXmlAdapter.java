package org.zhenchao.adapter.clazz;

import org.w3c.dom.Document;
import org.zhenchao.adapter.obj.AbstractXmlHandler;

import java.util.Properties;

/**
 * 适配器
 *
 * @author zhenchao.wang 2016-11-05 11:15
 * @version 1.0.0
 */
public class PropertiesToXmlAdapter extends GeneralPropertiesHandler implements AbstractXmlHandler {

    @Override
    public Document loadXml(String filepath) {
        Document document = null;
        Properties properties = super.loadProperties(filepath);
        // ... 执行properties到document的转换逻辑
        return document;
    }

    @Override
    public boolean saveXml(Document document, String filepath) {
        Properties properties = null;
        // ... 执行document到properties的转换逻辑
        return super.saveProperties(properties, filepath);
    }

}
