package com.graphics;

import com.animals.Animal;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ZooPanel extends JPanel implements Runnable, ActionListener {
    private JPanel actionPanel;
    private JPanel animalPanel;
    private JDialog addAnimalDialog;
    private JDialog moveAnimalDialog;
    private JFrame tableFrame;
    private ArrayList<Animal> animals;

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
        String[] options = {"Lettuce", "Cabbage", "Meat"};
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
                for (int i = 0; i < 10; i++){
                    names.add("name"+i);
                }
                moveAnimalDialog = new MoveAnimalDialog(names);
                moveAnimalDialog.getContentPane();
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
                InfoTableMVC table = new InfoTableMVC(animals);

                System.out.println("Info pressed!");
            }
            case "Exit" -> {
                System.exit(1);
            }
        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
