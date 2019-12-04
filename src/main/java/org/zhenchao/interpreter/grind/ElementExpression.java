package org.zhenchao.interpreter.grind;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenchao.wang 2019-12-04 14:05
 * @version 1.0.0
 */
public class ElementExpression extends XmlExpression {

    private List<XmlExpression> expressions = new ArrayList<>();

    private String name;

    public ElementExpression(String name) {
        this.name = name;
    }

    @Override
    public String[] interpret(Context context) {
        Element preElement = context.getPreElement();
        if (null == preElement) {
            // 获取根元素
            context.setPreElement(context.getDocument().getDocumentElement());
        } else {
            context.setPreElement(context.getNowElement(preElement, name));
        }

        // 循环调用子元素的 interpret 方法
        for (final XmlExpression expression : expressions) {
            expression.interpret(context);
        }
        return null;
    }

    public void add(XmlExpression expression) {
        expressions.add(expression);
    }

    public void remove(XmlExpression expression) {
        expressions.remove(expression);
    }

}
