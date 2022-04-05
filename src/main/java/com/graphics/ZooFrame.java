package com.graphics;

import com.graphics.menu.ZooMenuBar;

import javax.swing.*;

public class ZooFrame extends JFrame {
    private JMenuBar menuBar;
    private ZooPanel zooPanel;


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JMenuBar menuBar = new ZooMenuBar();
        frame.setJMenuBar(menuBar);
        frame.setTitle("Zooshit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
