package org.zhenchao.adapter.clazz;

import org.w3c.dom.Document;

/**
 * 抽象XML文件操作类
 *
 * @author zhenchao.wang 2016-11-05 11:04
 * @version 1.0.0
 */
public interface AbstractXmlHandler {

    /**
     * 加载XML文件
     *
     * @param filepath
     * @return
     */
    Document loadXml(String filepath);

    /**
     * 存储XML文件
     *
     * @param document
     * @param filepath
     * @return
     */
    boolean saveXml(Document document, String filepath);

}
