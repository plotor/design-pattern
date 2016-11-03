package org.zhenchao.adapter.obj;

/**
 * 接口适配器
 *
 * @author zhenchao.wang 2016-11-03 21:29
 * @version 1.0.0
 */
public class InterfaceAdapter implements NewMainBoard {

    private OldMainBoard oldMainBoard;

    public InterfaceAdapter(OldMainBoard oldMainBoard) {
        this.oldMainBoard = oldMainBoard;
    }

    @Override
    public void dp() {
        this.oldMainBoard.vga();
    }

    @Override
    public void hdmi() {
        this.oldMainBoard.vga();
    }

    @Override
    public void usb() {
        this.oldMainBoard.pci();
    }
}
