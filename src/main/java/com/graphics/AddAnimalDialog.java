package com.graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalDialog extends JDialog {
    private static AddAnimalDialog instance = null;
    private final String[] animalTypes = {"Lion", "Bear", "Giraffe", "Elephant", "Turtle"};
    private final String[] animalColors = {"Natural", "Red", "Blue"};
    private final JComboBox<String> cmbAnimalTypes;
    private final JComboBox<String> cmbAnimalColors;
    private final JButton btnAddAnimal;
    private final JFrame frame;
    private final JPanel panel;

    public static AddAnimalDialog getInstance(){
        if (instance == null) {
            instance = new AddAnimalDialog();
        }
        return instance;
    }

    private AddAnimalDialog(){
        frame = new JFrame("Add Animal");
        panel = new JPanel();

        cmbAnimalTypes = new JComboBox<>(animalTypes);
        cmbAnimalColors = new JComboBox<>(animalColors);

        JTextField txfSizeAnimal = new JTextField();
        txfSizeAnimal.setToolTipText("50 ~ 300");

        JLabel size = new JLabel("Size: ", JLabel.TRAILING);
        size.setLabelFor(txfSizeAnimal);
        JLabel lblType = new JLabel("Type:");
        lblType.setLabelFor(cmbAnimalTypes);
        JLabel lblWeight = new JLabel("Weight:");
        JLabel lblUnique = new JLabel("Unique:");
        JLabel lblColor = new JLabel("Color:");

        JLabel lblVSpeed = new JLabel("Vertical Speed: ");
        JLabel lblHSpeed = new JLabel("Horizontal Speed: ");
        JTextField txfVSpeed = new JTextField("5");
        JTextField txfHSpeed = new JTextField("6");


        btnAddAnimal = new JButton("Add Animal");

        // TODO: set default output to unique.
        cmbAnimalTypes.addActionListener(new ActionListener() {
            String unique;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbAnimalTypes.getSelectedIndex() == 0) {
                    unique = "Scar count: 0";
                }
                if (cmbAnimalTypes.getSelectedIndex() == 1) {
                    unique = "Fur color: GRAY";
                }
                if (cmbAnimalTypes.getSelectedIndex() == 2) {
                    unique = "Neck length: 1.0 - 3.0";
                }
                if (cmbAnimalTypes.getSelectedIndex() == 3) {
                    unique = "Trunk length: 1.0 - 3.0";
                }
                if (cmbAnimalTypes.getSelectedIndex() == 4) {
                    unique = "Age: 1 - 500";
                }

                lblUnique.setText("Unique: " + unique);
            }
        });

        btnAddAnimal.addActionListener(new ActionListener() {
           double animalWeight = 0;

           @Override
           public void actionPerformed(ActionEvent e) {
               int sizeOfAnimal = Integer.parseInt(txfSizeAnimal.getText());

               if (cmbAnimalTypes.getSelectedIndex() == 0) {
                   animalWeight = sizeOfAnimal * 0.8;
               }
               if (cmbAnimalTypes.getSelectedIndex() == 1) {
                   animalWeight = sizeOfAnimal * 1.5;
               }
               if (cmbAnimalTypes.getSelectedIndex() == 2) {
                   animalWeight = sizeOfAnimal * 2.2;
               }
               if (cmbAnimalTypes.getSelectedIndex() == 3) {
                   animalWeight = sizeOfAnimal * 10;
               }
               if (cmbAnimalTypes.getSelectedIndex() == 4) {
                   animalWeight = sizeOfAnimal * 0.5;
               }

               int vSpeed = Integer.parseInt(txfVSpeed.getText());
               int hSpeed = Integer.parseInt(txfHSpeed.getText());

               System.out.println(vSpeed);
               System.out.println(hSpeed);

               lblWeight.setText("Weight: ");
               lblWeight.setText(lblWeight.getText() + " " + animalWeight);
               lblVSpeed.setText("Vertical Speed: " +  vSpeed);
               lblHSpeed.setText("Horizontal Speed: " +  hSpeed);
           }
       });

        panel.add(txfVSpeed);
        panel.add(lblVSpeed);
        panel.add(txfHSpeed);
        panel.add(lblHSpeed);
        panel.add(lblColor);
        panel.add(cmbAnimalColors);
        panel.add(lblUnique);
        panel.add(lblWeight);
        panel.add(lblType);
        panel.add(cmbAnimalTypes);
        panel.add(size);
        panel.add(txfSizeAnimal);
        panel.add(btnAddAnimal);

        frame.add(panel);
        // frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setSize(100,100);
        frame.setVisible(true);
    }
}
