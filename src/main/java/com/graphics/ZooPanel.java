package com.graphics;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ZooPanel extends JPanel implements Runnable, ActionListener {
    private JPanel actionPanel;
    private JDialog addAnimalDialog;
    private JFrame tableFrame;

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

    private void createFoodDialog(){
//        Object[] possibilities = {"Lettuce", "Cabbage", "Meat"};
//        String s = (String)JOptionPane.showInputDialog(
//                null,
//                "What food would you like to add?",
//                "Add Food Dialog",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                possibilities,
//                "ham");
        Object[] options = {"Lettuce", "Cabbage", "Meat"};
        int result = JOptionPane.showOptionDialog(null,//parent container of JOptionPane
                "What food would you like to add?",
                "Add Food Dialog",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                null);
        if (result == JOptionPane.YES_OPTION){System.out.println("Lettuce");}
        else if (result == JOptionPane.NO_OPTION){System.out.println("Cabbage");}
        else if (result == JOptionPane.CANCEL_OPTION){System.out.println("Meat");}
    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Add Animal" -> {
                addAnimalDialog = new AddAnimalDialog();
            }
            case "Move Animal" -> {
                ArrayList<String> names = new ArrayList<>();
                names.add("name1");
                names.add("name2");
                names.add("name3");
                names.add("name4");
                names.add("name5");
                names.add("name6");
                names.add("name7");
                names.add("name8");
                names.add("name9");
                names.add("name10");
                JDialog dialog = new MoveAnimalDialog(names);
                dialog.getContentPane();
                System.out.println("Move Animal pressed!");
            }
            case "Clear" -> {
                System.out.println("Clear pressed!");
            }
            case "Food" -> {
                createFoodDialog();
                System.out.println("Food pressed!");
            }
            case "Info" -> {
                //creating animals details list
                ArrayList<ArrayList<String>> animalsDetails = new ArrayList<>();
                //creating first animal details
                ArrayList<String> animalDetails1 = new ArrayList<>();
                animalDetails1.add("Elephant");
                animalDetails1.add("NATURAL");
                animalDetails1.add("2000");
                animalDetails1.add("5");
                animalDetails1.add("5");
                animalDetails1.add("4");
                //creating second animal details
                ArrayList<String> animalDetails2 = new ArrayList<>();
                animalDetails2.add("Lion");
                animalDetails2.add("NATURAL");
                animalDetails2.add("380");
                animalDetails2.add("8");
                animalDetails2.add("8");
                animalDetails2.add("5");
                //adding the details to the main array
                animalsDetails.add(animalDetails1);
                animalsDetails.add(animalDetails2);
                tableFrame = new ZooTable(animalsDetails, 9);

                System.out.println("Info pressed!");
            }
            case "Exit" -> {
                System.exit(1);
            }
        }
    }
}
