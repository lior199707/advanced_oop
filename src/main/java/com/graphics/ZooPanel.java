package com.graphics;

import com.plants.Cabbage;
import com.plants.Lettuce;
import com.plants.Plant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZooPanel extends JPanel implements ActionListener {
    private JPanel actionPanel;
    private JPanel animalPanel;
    private JDialog addAnimalDialog;
    private JDialog moveAnimalDialog;
    private JFrame tableFrame;
    private AnimalModel model;
    private Plant plant = null;
    private boolean isInfoOpen;

    public ZooPanel(){
        model = new AnimalModel();
        actionPanel = new JPanel();
        animalPanel = new AnimalPanel(model, plant);

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

        // setting up animalPanel
        animalPanel.setLocation(500,500);
        animalPanel.setSize(new Dimension(800,600));
        animalPanel.setBackground(Color.BLACK);

        // setting properties
        Font courier = new Font("Courier", Font.PLAIN, 14);
        TitledBorder actionBorder = BorderFactory.createTitledBorder("Select Option:");
        actionBorder.setTitleFont(courier);
        actionBorder.setTitleColor(Color.blue);

        actionPanel.setBorder(actionBorder);
        this.setLayout(new BorderLayout());
        this.add(animalPanel, BorderLayout.CENTER);
        this.add(actionPanel, BorderLayout.SOUTH);
    }

    private void createFoodDialog(){
        String[] options = {"Lettuce", "Cabbage", "Meat"};
        int result = JOptionPane.showOptionDialog(null,//parent container of JOptionPane
                "What food would you like to add?",
                "Add Food Dialog",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                null);
        if (result == JOptionPane.YES_OPTION){
            plant = new Lettuce();
        }
        else if (result == JOptionPane.NO_OPTION){
            plant = new Cabbage();
        }
        else if (result == JOptionPane.CANCEL_OPTION){System.out.println("Meat");}
    }


    public void manageZoo() {
    }
//
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Hello from ZooPanel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Add Animal" -> {
                addAnimalDialog = new AddAnimalDialog(model);
            }
            case "Move Animal" -> {
                if (model.getModelSize() > 0) {
                    moveAnimalDialog = new MoveAnimalDialog(model);
                    moveAnimalDialog.getContentPane();
                } else {
                    String message = "Zoo is currently empty!";
                    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            case "Clear" -> {
                model.removeAllAnimals();
            }
            case "Food" -> {
                createFoodDialog();
                System.out.println("Food pressed!");
            }
            case "Info" -> {
                //creating animals details list
                if (model.getModelSize() > 0) {
                    if (!InfoTableMVC.getIsOpen()) {
                        InfoTableMVC table = new InfoTableMVC(model);
                        table.setVisible(true);
                    }
                } else {
                    String message = "Zoo is currently empty!";
                    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            case "Exit" -> {
                System.exit(1);
            }
        }
//        manageZoo();
    }

    public JPanel getAnimalPanel() {
        return animalPanel;
    }
}
