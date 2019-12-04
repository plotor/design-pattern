package org.zhenchao.interpreter.grind;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 上下文，封装解释器需要的一些全局数据和公共功能
 *
 * @author zhenchao.wang 2019-12-04 13:51
 * @version 1.0.0
 */
public class Context {

    private Document document;

    /** 上一个被处理的元素 */
    private Element preElement;

    public Context(String filepath) throws Exception {
        // 获取 xml 对应的 Document 对象
        this.document = XmlUtils.getDocument(filepath);
    }

    /**
     * 重新初始化
     */
    public void reInit() {
        preElement = null;
    }

    /**
     * 获取父元素下指定名称的元素
     *
     * @param preElement 父元素
     * @param elementName 目标元素名称
     * @return 目标元素
     */
    public Element getNowElement(Element preElement, String elementName) {
        NodeList childNodes = preElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                if (elementName.equals(element.getTagName())) {
                    return element;
                }
            }
        }
        return null;
    }

    public Element getPreElement() {
        return preElement;
    }

    public Context setPreElement(Element preElement) {
        this.preElement = preElement;
        return this;
    }

    public Document getDocument() {
        return document;
    }
}
