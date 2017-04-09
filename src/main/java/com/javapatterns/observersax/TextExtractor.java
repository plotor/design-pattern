package com.javapatterns.observersax;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.Writer;

public class TextExtractor extends DefaultHandler {

    private Writer out;

    public TextExtractor(Writer out) {
        this.out = out;
    }

    public void characters(char[] text, int start, int length)
            throws SAXException {
        try {
            out.write(text, start, length);
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

}
