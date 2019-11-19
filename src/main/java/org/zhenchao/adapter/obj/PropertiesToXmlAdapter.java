package org.zhenchao.adapter.obj;

import org.w3c.dom.Document;

import java.util.Properties;

/**
 * 适配器
 *
 * @author zhenchao.wang 2016-11-05 11:15
 * @version 1.0.0
 */
public class PropertiesToXmlAdapter implements AbstractXmlHandler {

    private AbstractPropertiesHandler abstractPropertiesHandler;

    public PropertiesToXmlAdapter(AbstractPropertiesHandler abstractPropertiesHandler) {
        this.abstractPropertiesHandler = abstractPropertiesHandler;
    }

    @Override
    public Document loadXml(String filepath) {
        Document document = null;
        Properties properties = abstractPropertiesHandler.loadProperties(filepath);
        // ... 执行properties到document的转换逻辑
        return document;
    }

    @Override
    public boolean saveXml(Document document, String filepath) {
        Properties properties = null;
        // ... 执行document到properties的转换逻辑
        return abstractPropertiesHandler.saveProperties(properties, filepath);
    }

}
