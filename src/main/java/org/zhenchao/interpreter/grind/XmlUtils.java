package org.zhenchao.interpreter.grind;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author zhenchao.wang 2019-12-04 13:53
 * @version 1.0.0
 */
public class XmlUtils {

    public static Document getDocument(String filepath)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filepath);
        document.normalize();
        return document;
    }

}
