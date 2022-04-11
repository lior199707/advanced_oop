package com.graphics;

import com.privateutil.PrivateGraphicUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddAnimalDialog extends JDialog {
    private final AddAnimalDialogListener listener;
    private final String[] animalTypes = {"Lion", "Bear", "Giraffe", "Elephant", "Turtle"};
    private final String[] animalColors = {"Natural", "Red", "Blue"};
    private JComboBox<String> animalTypesCmb;
    private JComboBox<String> animalColorsCmb;

    private JTextField sizeTextField;
    private JTextField vSpeedTextField;
    private JTextField hSpeedTextField;
    private JTextField nameTextField;

    private JLabel sizeLabel;
    private JLabel vSpeedLabel;
    private JLabel hSpeedLabel;
    private JLabel colorLabel;
    private JLabel nameLabel;

    private JLabel locationLabel;
    private JLabel weightLabel;
    private JLabel uniqueLabel;

    public AddAnimalDialog() {
        int dialogX = 500, dialogY = 350;

        // configurations
        this.setModal(true);
        this.setTitle("Add Animal");
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLocation(PrivateGraphicUtils.centerWindow(dialogX,dialogY));
        this.setSize(new Dimension(dialogX, dialogY));
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        getRootPane().getParent(), "Are you sure?");
                if (result == JOptionPane.OK_OPTION) {
                    // NOW we change it to dispose on close.
                    setDefaultCloseOperation(
                            JFrame.DISPOSE_ON_CLOSE);
                    setVisible(false);
                    dispose();
                }
            }
        });

        this.createDialog(this);
        this.setVisible(true);
        this.pack();

        listener = new AddAnimalDialogListener();
    }

    public JPanel createNorthPanel() {
        JPanel animalTypePanel = new JPanel();
        animalTypesCmb = new JComboBox<>(animalTypes);

        // setting border
        TitledBorder animalChoiceBorder = BorderFactory.createTitledBorder("Choose Animal: ");
        animalTypesCmb.setBorder(animalChoiceBorder);

        // adding to action listener.
        animalTypesCmb.addActionListener(listener);

        // adding the combobox to the panel.
        animalTypePanel.add(animalTypesCmb);

        return animalTypePanel;
    }

    public JPanel createSouthPanel() {
        JPanel buttonPanel = new JPanel();

        JButton addAnimalButton = new JButton("Add Animal");
        JButton validateButton = new JButton("Validate");

        // disabling addAnimalButton. will enable after pressing validate - in action listener.
        addAnimalButton.setEnabled(false);

        // adding to action listener.
        addAnimalButton.addActionListener(listener);
        validateButton.addActionListener(listener);

        // adding buttons to button panel.
        buttonPanel.add(validateButton);
        buttonPanel.add(addAnimalButton);
        return buttonPanel;
    }

    public JPanel createEastPanel() {
        // TODO:
        // -------------- MAKE A METHOD FOR ADDING PICTURES -----------
        BufferedImage picture = null;
        Image modifiedImage = null;
        try {
            picture = ImageIO.read(new File("/Users/sagebaram/workspace/college/current/advanced_oop/assignments/project/src/main/resources/assignment2_pictures/lio_n_1.png"));
            ImageIcon image = new ImageIcon(picture);
            Image toSizeImage = image.getImage();
            modifiedImage = toSizeImage.getScaledInstance(220, 180, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert picture != null;

        JLabel image = new JLabel(new ImageIcon(modifiedImage));
        // -------------- MAKE A METHOD FOR ADDING PICTURES -----------

        JPanel imagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcImagePanel = new GridBagConstraints();
        TitledBorder border = BorderFactory.createTitledBorder("Picture");
        border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
        image.setBorder(border);

        gbcImagePanel.gridx = 5;
        gbcImagePanel.gridy = 5;
        gbcImagePanel.insets = new Insets(0, 0, 0, 10);
        imagePanel.add(image, gbcImagePanel);

        return imagePanel;
    }

    // creating west panel containing user input & dynamic fields.
    public JPanel createWestPanel() {
        JPanel inputPanel = new JPanel();
        JPanel northInputPanel = new JPanel();
        JPanel southInputPanel = new JPanel();

        inputPanel.setLayout(new BorderLayout());
        northInputPanel.setLayout(new GridBagLayout());
        southInputPanel.setLayout(new GridBagLayout());

        // create north input panel components

        nameLabel = new JLabel("Name:");
        sizeLabel = new JLabel("Size:");
        vSpeedLabel = new JLabel("V_Speed:");
        hSpeedLabel = new JLabel("H_Speed:");
        colorLabel = new JLabel("Color:");

        nameTextField = new JTextField(10);
        sizeTextField = new JTextField("50-300", 10);
        vSpeedTextField = new JTextField("1-10", 10);
        hSpeedTextField = new JTextField("1-10", 10);
        animalColorsCmb = new JComboBox<>(animalColors);

        // setting action commands
        sizeTextField.setActionCommand("SizeTF");

        // setting color for placeholder text.
        sizeTextField.setForeground(Color.GRAY);
        vSpeedTextField.setForeground(Color.GRAY);
        hSpeedTextField.setForeground(Color.GRAY);

        // focus listener for input. reset if lost focus & empty.
        addValidRangeFocusListener(sizeTextField,50,300);
        addValidRangeFocusListener(vSpeedTextField, 1, 10);
        addValidRangeFocusListener(hSpeedTextField,1,10);

        // action listener for input - TODO: DO WE NEED ACTION LISTENERS HERE?
        sizeTextField.addActionListener(listener);
        vSpeedTextField.addActionListener(listener);
        hSpeedTextField.addActionListener(listener);
        nameTextField.addActionListener(listener);

        // create south input panel components
        locationLabel = new JLabel("Location:");
        weightLabel = new JLabel("Weight:");
        uniqueLabel = new JLabel("Unique:");


        // setting layout constraints for north panel.
        GridBagConstraints gbcNorthInputPanel = new GridBagConstraints();

        gbcNorthInputPanel.insets = new Insets(0, 10, 0, 0);
        gbcNorthInputPanel.anchor = GridBagConstraints.LINE_START;

        // label constraints
        gbcNorthInputPanel.gridx = 0;
        gbcNorthInputPanel.gridy = 0;
        northInputPanel.add(nameLabel, gbcNorthInputPanel);

        gbcNorthInputPanel.gridx = 0;
        gbcNorthInputPanel.gridy = 1;
        northInputPanel.add(sizeLabel, gbcNorthInputPanel);

        gbcNorthInputPanel.gridx = 0;
        gbcNorthInputPanel.gridy = 2;
        northInputPanel.add(vSpeedLabel, gbcNorthInputPanel);

        gbcNorthInputPanel.gridx = 0;
        gbcNorthInputPanel.gridy = 3;
        northInputPanel.add(hSpeedLabel, gbcNorthInputPanel);

        gbcNorthInputPanel.gridx = 0;
        gbcNorthInputPanel.gridy = 4;
        northInputPanel.add(colorLabel, gbcNorthInputPanel);

        // input constraints.
        gbcNorthInputPanel.gridx = 1;
        gbcNorthInputPanel.gridy = 0;
        northInputPanel.add(nameTextField, gbcNorthInputPanel);

        gbcNorthInputPanel.gridx = 1;
        gbcNorthInputPanel.gridy = 1;
        northInputPanel.add(sizeTextField, gbcNorthInputPanel);

        gbcNorthInputPanel.gridx = 1;
        gbcNorthInputPanel.gridy = 2;
        northInputPanel.add(vSpeedTextField, gbcNorthInputPanel);

        gbcNorthInputPanel.gridx = 1;
        gbcNorthInputPanel.gridy = 3;
        northInputPanel.add(hSpeedTextField, gbcNorthInputPanel);

        gbcNorthInputPanel.gridx = 1;
        gbcNorthInputPanel.gridy = 4;
        northInputPanel.add(animalColorsCmb, gbcNorthInputPanel);

        // setting layout constraints for south panel.
        GridBagConstraints gbcSouthInputPanel = new GridBagConstraints();

        gbcSouthInputPanel.anchor = GridBagConstraints.LINE_START;
        gbcSouthInputPanel.weightx = 1;
        gbcSouthInputPanel.insets = new Insets(0,5,0,0);

        gbcSouthInputPanel.gridx = 0;
        gbcSouthInputPanel.gridy = 5;
        southInputPanel.add(weightLabel, gbcSouthInputPanel);

        gbcSouthInputPanel.gridx = 0;
        gbcSouthInputPanel.gridy = 6;
        southInputPanel.add(uniqueLabel, gbcSouthInputPanel);

        gbcSouthInputPanel.gridx = 0;
        gbcSouthInputPanel.gridy = 7;
        southInputPanel.add(locationLabel, gbcSouthInputPanel);

        // setting up a titled border.
        TitledBorder northInputBorder = BorderFactory.createTitledBorder("Input: ");
        northInputPanel.setBorder(northInputBorder);

        // positioning the panels in north & south
        inputPanel.add(northInputPanel, BorderLayout.NORTH);
        inputPanel.add(southInputPanel, BorderLayout.SOUTH);

        // return the input panel.
        return inputPanel;
    }



    // setting up panels on given frame.
    public void createDialog(JDialog dialog) {
        dialog.getContentPane().add(createNorthPanel(), BorderLayout.NORTH);
        dialog.getContentPane().add(createEastPanel(), BorderLayout.EAST);
        dialog.getContentPane().add(createWestPanel(), BorderLayout.WEST);
        dialog.getContentPane().add(createSouthPanel(), BorderLayout.SOUTH);
    }


    /**
     * AddAnimalDialogListener is a private class implementing ActionListener.
     * it responds to certain events occurring in AddAnimalDialog.
     */
    private class AddAnimalDialogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            double animalWeight = 0;
            switch (command) {
                case "SizeTF" -> {
                    int sizeOfAnimal = Integer.parseInt(sizeTextField.getText());
                    // Lion
                    if (animalTypesCmb.getSelectedIndex() == 0) {
                        animalWeight = sizeOfAnimal * 0.8;
                    }
                    if (animalTypesCmb.getSelectedIndex() == 1) {
                        animalWeight = sizeOfAnimal * 1.5;
                    }
                    if (animalTypesCmb.getSelectedIndex() == 2) {
                        animalWeight = sizeOfAnimal * 2.2;
                    }
                    if (animalTypesCmb.getSelectedIndex() == 3) {
                        animalWeight = sizeOfAnimal * 10;
                    }
                    if (animalTypesCmb.getSelectedIndex() == 4) {
                        animalWeight = sizeOfAnimal * 0.5;
                    }
                    weightLabel.setText("Weight: " + animalWeight);
                }
            }
        }
    }

    /**
     * addValidRangeFocusListener using FocusListener to dynamically change JTextField values.
     * @param validRangeTextField JTextField object to set focus on/off.
     * @param MIN_RANGE integer value of the minimum range.
     * @param MAX_RANGE integer value of the maximum range.
     */
    private void addValidRangeFocusListener(JTextField validRangeTextField,int MIN_RANGE, int MAX_RANGE) {
        validRangeTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (validRangeTextField.getText().equals(MIN_RANGE + "-" + MAX_RANGE)) {
                    validRangeTextField.setText("");
                    validRangeTextField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (validRangeTextField.getText().isEmpty()) {
                    validRangeTextField.setForeground(Color.GRAY);
                    validRangeTextField.setText(MIN_RANGE + "-" + MAX_RANGE);
                }
            }
        });
    }
}
