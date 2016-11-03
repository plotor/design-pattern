package org.zhenchao.adapter.obj;

/**
 * @author zhenchao.wang 2016-11-03 21:34
 * @version 1.0.0
 */
public class Test {

    public static void main(String[] args) {

        NewMainBoard newMainBoard = new InterfaceAdapter(new OldMainBoard());
        newMainBoard.dp();
        newMainBoard.hdmi();
        newMainBoard.usb();

    }

}
