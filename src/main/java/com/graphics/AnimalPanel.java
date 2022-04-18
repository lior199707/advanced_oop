package com.graphics;

import com.animals.Animal;
import com.plants.Plant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class AnimalPanel extends JPanel {
    private Plant plant;
    private AnimalModel model;
    private JButton buttontest;

    public AnimalPanel(AnimalModel model, Plant plant){
        this.model = model;
        this.plant = plant;
        this.buttontest = new JButton("Test");
        this.add(buttontest);
        TitledBorder border = BorderFactory.createTitledBorder("Shit");
        this.setBorder(border);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ArrayList<Animal> animals = model.getModel();
        for (Animal animal : animals) {
            animal.drawObject(g);
            repaint();
        }

        if (plant != null) {
            plant.drawObject(g);
            repaint();
        }
    }
}
