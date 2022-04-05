package com.graphics.menu;

import javax.swing.*;

public class ZooMenuBar extends JMenuBar {
    private JMenu file;
    private JMenu background;
    private JMenu help;

    public ZooMenuBar() {
        this.file = new JMenu("File");
        this.background = new JMenu("Background");
        this.help = new JMenu("Help");

        setFileMenu();
        setBackgroundMenu();
        setHelpMenu();
    }

    private void setFileMenu(){
        JMenuItem exit = new JMenuItem("Exit");
        this.file.add(exit);
    }

    private void setBackgroundMenu(){
        JMenuItem image = new JMenuItem("Image");
        JMenuItem green = new JMenuItem("Green");
        JMenuItem none = new JMenuItem("None");
        this.background.add(image);
        this.background.add(green);
        this.background.add(none);
    }

    private void setHelpMenu(){
        JMenuItem help = new JMenuItem("Help");
        this.help.add(help);
    }
}
