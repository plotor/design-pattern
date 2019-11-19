package org.zhenchao.adapter.twodirect;

import org.w3c.dom.Document;
import org.zhenchao.adapter.obj.AbstractPropertiesHandler;
import org.zhenchao.adapter.obj.AbstractXmlHandler;

import java.util.Properties;

/**
 * 双向适配器
 *
 * @author zhenchao.wang 2016-11-05 11:33
 * @version 1.0.0
 */
public class TwoDirectAdapter implements AbstractPropertiesHandler, AbstractXmlHandler {

    private AbstractPropertiesHandler propertiesHandler;

    private AbstractXmlHandler xmlHandler;

    public TwoDirectAdapter(AbstractPropertiesHandler propertiesHandler, AbstractXmlHandler xmlHandler) {
        this.propertiesHandler = propertiesHandler;
        this.xmlHandler = xmlHandler;
    }

    @Override
    public Document loadXml(String filepath) {
        Document document = null;
        Properties properties = propertiesHandler.loadProperties(filepath);
        // ... 执行properties到document的转换逻辑
        return document;
    }

    @Override
    public boolean saveXml(Document document, String filepath) {
        Properties properties = null;
        // ... 执行document到properties的转换逻辑
        return propertiesHandler.saveProperties(properties, filepath);
    }

    @Override
    public Properties loadProperties(String filepath) {
        Properties properties = null;
        Document document = xmlHandler.loadXml(filepath);
        // ... 执行document到properties的转换逻辑
        return properties;
    }

    @Override
    public boolean saveProperties(Properties properties, String filepath) {
        Document document = null;
        // ... 执行properties到document的转换逻辑
        return xmlHandler.saveXml(document, filepath);
    }

}
