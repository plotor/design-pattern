package com.javapatterns.observersax;

import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.OutputStreamWriter;
import java.io.Writer;

public class MyReaderShort {
    private static XMLReader parser = null;
    private static ContentHandler handler = null;

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            parser = XMLReaderFactory.createXMLReader();

            Writer out = new OutputStreamWriter(System.out);
            handler = new MyHandlerShort(out);
            parser.setContentHandler(handler);

            parser.parse("myxml.xml");

            out.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

