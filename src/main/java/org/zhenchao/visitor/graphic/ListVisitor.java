package org.zhenchao.visitor.graphic;

import java.util.Iterator;

/**
 * @author zhenchao.wang 2019-11-25 10:43
 * @version 1.0.0
 */
public class ListVisitor extends Visitor {

    private String currentDir = "";

    @Override
    public void visit(File file) {
        System.out.println(currentDir + "/" + file.toString());
    }

    @Override
    public void visit(Directory directory) {
        System.out.println(currentDir + "/" + directory.toString());
        String saveDir = currentDir;
        currentDir = currentDir + "/" + directory.getName();
        final Iterator itr = directory.iterator();
        while (itr.hasNext()) {
            Entry entry = (Entry) itr.next();
            entry.accept(this);
        }
        currentDir = saveDir;
    }
}
