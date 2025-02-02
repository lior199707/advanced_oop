package com.graphics;

import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZooFrame extends JFrame{
    private JMenuBar menuBar;
    private ZooPanel zooPanel;

    ZooFrame(){
        menuBar = constructJMenuBar();
        zooPanel = new ZooPanel();

        this.add(zooPanel);
        this.setJMenuBar(menuBar);

        int frameX = 1000, frameY = 850;
        this.setSize(frameX,frameY);
        this.setLocation(PrivateGraphicUtils.centerWindow(frameX,frameY));
        this.setResizable(false);
        this.setTitle("Zoo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
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
        JMenuItem manualMenuItem = new JMenuItem("Manual");


        file.add(exit);

        background.add(image);
        background.add(green);
        background.add(none);

        help.add(helpMenuItem);
        help.add(manualMenuItem);

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
        manualMenuItem.addActionListener(listener);

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
                    zooPanel.setImageBackground();
                }
                case "Green" -> {
                    zooPanel.setGreenBackground();
                }
                case "None" -> {
                    zooPanel.setNoBackground();
                }
                case "Help" -> {
                    String message = "Home Work 2\nGUI";
                    try {
                        throw new PrivateGraphicUtils.InformationDialogException(null, message);
                    } catch (PrivateGraphicUtils.InformationDialogException ignored) {}
                }
                case "Manual" -> {
                    String message = """
                            OPTIONS:
                            1.Add animals- lets you add animals to the zoo, up to 10 animals in the zoo
                            2.Move animals- lets you move the animals to another location
                            3.Clear - deletes all the animals in the zoo, doesn't affects food though.
                            4.Add food - lets you choose the type of food: Lettuce, Meat, Cabbage.
                            5.Info- lets you see the zoo animals information.
                            6.Exit - exits the program.
                            
                            IMPORTANT SIDE NOTES:
                            *When adding a new animal please check that there are no predators on the same coordinates, otherwise the animal will be instantly eaten
                            *When adding a new animal red text fields indicate invalid input and black text fields indicate valid input
                            *Food spawns in the middle of the screen(400, 300).
                            *A predator can eat more than a single prey at a time
                            *We recommend to leave the info table open, it will exhibit every information changes about the animals dynamically.""";
                    try {
                        throw new PrivateGraphicUtils.InformationDialogException(null, message);
                    } catch (PrivateGraphicUtils.InformationDialogException ignored) {};
                }
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZooFrame();
            }
        });
    }
}
