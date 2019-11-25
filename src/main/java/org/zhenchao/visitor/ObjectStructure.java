package org.zhenchao.visitor;

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * @author zhenchao.wang 2019-11-25 13:45
 * @version 1.0.0
 */
public class ObjectStructure {

    private Collection<Element> elements = new ArrayDeque<>();

    public void handleRequest(Visitor visitor) {
        for (final Element element : elements) {
            element.accept(visitor);
        }
    }

    public void add(Element element) {
        elements.add(element);
    }

}
