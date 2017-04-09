package com.javapatterns.adapter.windowadapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

class SwingUI extends JFrame implements ActionListener {

    JLabel text, clicked;
    JButton button, clickButton;
    JPanel panel;
    private boolean m_clickMeMode = true;

    /**
     * @label Uses
     * @directed
     */
    private WindowAdapter lnkWindowAdapter;

    SwingUI() {
        text = new JLabel("�Һܸ��ˣ�");
        button = new JButton("����");
        button.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        getContentPane().add(panel);
        panel.add(BorderLayout.CENTER, text);
        panel.add(BorderLayout.SOUTH, button);
    }

    public static void main(String[] args) {
        SwingUI frame = new SwingUI();
        frame.setTitle("��");
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        frame.addWindowListener(listener);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (m_clickMeMode) {
            text.setText("�Һܷ���");
            button.setText("������");
            m_clickMeMode = false;
        } else {
            text.setText("�Һܸ��ˣ�");
            button.setText("����");
            m_clickMeMode = true;
        }
    }
}
