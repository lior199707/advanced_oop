package com.graphics;

import javax.swing.*;

public class MoveAnimalDialog extends JDialog {
    private static MoveAnimalDialog instance = null;
    JFrame frame;
    JPanel panel;

    private MoveAnimalDialog(){
        frame = new JFrame("Move Animal");
        panel = new JPanel();
        JLabel label = new JLabel("wejjkwejr");
        frame.add(panel);
        frame.add(label);


        frame.setSize(100,100);
        frame.setVisible(true);

    }

    public static MoveAnimalDialog getInstance(){
        if (instance == null) {
            instance = new MoveAnimalDialog();
        }
        return instance;
    }
}
