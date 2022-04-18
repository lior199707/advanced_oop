package com.graphics;

import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZooFrame extends JFrame{
    private final JMenuBar menuBar;
    private final JPanel zooPanel;

    ZooFrame(){
        menuBar = constructJMenuBar();
        zooPanel = new ZooPanel();

        this.add(zooPanel);
        this.setJMenuBar(menuBar);

        int frameX = 1000, frameY = 1000;
        this.setSize(frameX,frameY);

        this.setLocation(PrivateGraphicUtils.centerWindow(frameX,frameY));
        this.setTitle("Zoo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setContentPane(zooPanel);
    }

    private JMenuBar constructJMenuBar( ){
        JMenuBar menuBar = new JMenuBar();
        // initiating JMenu fields.

        JMenu file = new JMenu("File");
        JMenu background = new JMenu("Background");
        JMenu help = new JMenu("Help");

        // initiating items for file menu.
        JMenuItem exit = new JMenuItem("Exit");

        // initiating items for background menu.
        JMenuItem image = new JMenuItem("Image");
        JMenuItem green = new JMenuItem("Green");
        JMenuItem none = new JMenuItem("None");

        // initiating items for help menu.
        JMenuItem helpMenuItem = new JMenuItem("Help");


        file.add(exit);

        background.add(image);
        background.add(green);
        background.add(none);

        help.add(helpMenuItem);

        // adding menus to the menu bar.
        menuBar.add(file);
        menuBar.add(background);
        menuBar.add(help);

        ZooMenuBarListener listener = new ZooMenuBarListener();
        exit.addActionListener(listener);
        image.addActionListener(listener);
        green.addActionListener(listener);
        none.addActionListener(listener);
        helpMenuItem.addActionListener(listener);

        return menuBar;
    }

    private class ZooMenuBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            switch (actionCommand) {
                case  "Exit" -> {
                    System.exit(1);
                }
                case "Image" -> {
                    System.out.println("Background -> Image pressed.");
                }
                case "Green" -> {
                    System.out.println("Background -> Green pressed.");
                    zooPanel.setBackground(Color.green);
                }
                case "None" -> {
                    System.out.println("Background -> None pressed!");
                    zooPanel.setBackground(null);
                }
                case "Help" -> {
                    JOptionPane.showMessageDialog(null,"Home Work 2\nGUI", "Message", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Help -> Help pressed.");
                }
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new ZooFrame();
//        frame.setLayout(new BorderLayout(100,100));
        frame.setVisible(true);
    }
}
