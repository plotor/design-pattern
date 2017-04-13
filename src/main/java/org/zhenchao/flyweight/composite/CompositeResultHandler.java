package org.zhenchao.flyweight.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 结果处理器复合享元
 *
 * @author zhenchao.wang 2017-04-13 21:42
 * @version 1.0.0
 */
public class CompositeResultHandler extends AbstractResultHandler {

    /** 存储构造复合享元的单纯享元集合 */
    private List<AbstractResultHandler> handlers = new ArrayList<>();

    @Override
    public void handle(Object obj) {
        for (final AbstractResultHandler handler : handlers) {
            handler.handle(obj);
        }
    }

    @Override
    public int getPriority() {
        return BASE_PRIORITY;
    }

    public void add(AbstractResultHandler handler) {
        handlers.add(handler);
        // 对处理按照优先级由大到小排序
        Collections.sort(handlers);
    }

}
