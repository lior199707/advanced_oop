package com.graphics;

import com.animals.Animal;
import com.plants.Cabbage;
import com.plants.Lettuce;
import com.plants.Plant;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ZooPanel extends JPanel implements ActionListener {
    private JPanel actionPanel;
    private JDialog addAnimalDialog;
    private JDialog moveAnimalDialog;
    private JFrame tableFrame;
    private AnimalModel model;
    private Plant plant = null;
    private BufferedImage savana;

    public ZooPanel() {
        model = new AnimalModel();
        actionPanel = new JPanel();

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

        this.setLayout(new BorderLayout());
        this.add(actionPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (savana != null) {
            g.drawImage(savana, 0, 0,null);
        }

        if (plant != null)
            plant.drawObject(g);

        for (Animal animal : model.getModel()) {
            animal.drawObject(g);
        }
    }

    //    private void setPanelBackground()
    private void createFoodDialog() {
        String[] options = {"Lettuce", "Cabbage", "Meat"};
        int result = JOptionPane.showOptionDialog(null,//parent container of JOptionPane
                "What food would you like to add?",
                "Add Food Dialog",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                null);
        if (result == JOptionPane.YES_OPTION) {
            plant = new Lettuce();
            plant.setPan(this);
        } else if (result == JOptionPane.NO_OPTION) {
            plant = new Cabbage();
            plant.setPan(this);
        } else if (result == JOptionPane.CANCEL_OPTION) {
            System.out.println("Meat");
        }
    }

    public void manageZoo() {
        repaint();
    }

    public void setImageBackground(){
        try {
            savana = ImageIO.read(new File("src/main/resources/assignment2_pictures/background_images/savanna.png"));
            manageZoo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGreenBackground(){
        savana = null;
        manageZoo();
        this.setBackground(Color.green);
    }

    public void setNoBackground(){
        savana = null;
        this.setBackground(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Add Animal" -> {
                addAnimalDialog = new AddAnimalDialog(model,this);
            }
            case "Move Animal" -> {
                if (model.getModelSize() > 0) {
                    moveAnimalDialog = new MoveAnimalDialog(model, this);
                } else {
                    String message = "Zoo is currently empty!";
                    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            case "Clear" -> {
                model.removeAllAnimals();
                manageZoo();
            }
            case "Food" -> {
                createFoodDialog();
                manageZoo();
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
    }
}
