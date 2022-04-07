package com.graphics;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZooPanel extends JPanel implements Runnable, ActionListener {
    private JPanel actionPanel;

    public ZooPanel(){
        actionPanel = new JPanel();
//        animalPanel = new JPanel();
        // instantiating buttons
        JButton addAnimal = new JButton("Add Animal");
        JButton moveAnimal = new JButton("Move Animal");
        JButton clear = new JButton("Clear");
        JButton food = new JButton("Food");
        JButton info = new JButton("Info");
        JButton exit = new JButton("Exit");

        // adding to action listener
        addAnimal.addActionListener(this);
        moveAnimal.addActionListener(this);
        clear.addActionListener(this);
        food.addActionListener(this);
        info.addActionListener(this);
        exit.addActionListener(this);

        // adding buttons to panel
        actionPanel.add(addAnimal);
        actionPanel.add(moveAnimal);
        actionPanel.add(clear);
        actionPanel.add(food);
        actionPanel.add(info);
        actionPanel.add(exit);

        // setting properties
        Font courier = new Font("Courier", Font.PLAIN, 14);
        TitledBorder actionBorder = BorderFactory.createTitledBorder("Select Option:");
        actionBorder.setTitleFont(courier);
        actionBorder.setTitleColor(Color.blue);

        actionPanel.setBorder(actionBorder);
        this.add(actionPanel);
    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Add Animal" -> {
                JDialog dialog = AddAnimalDialog.getInstance();
                dialog.getContentPane();
                System.out.println("Add Animal pressed!");
            }
            case "Move Animal" -> {
                JDialog dialog = MoveAnimalDialog.getInstance();
                dialog.getContentPane();
                System.out.println("Move Animal pressed!");
            }
            case "Clear" -> {
                System.out.println("Clear pressed!");
            }
            case "Food" -> {
                System.out.println("Food pressed!");
            }
            case "Info" -> {
                System.out.println("Info pressed!");
            }
            case "Exit" -> {
                System.exit(1);
            }
        }
    }
}
