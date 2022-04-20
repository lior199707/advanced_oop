package com.graphics;

import com.animals.*;
import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

import static com.privateutil.PrivateGraphicUtils.findAnimalImagePath;

public class AddAnimalDialog extends JDialog {
    private static final int DEFAULT_DIRECTION = 1;

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
    private JLabel imageLabel;

    private JButton addAnimalButton;
    private JButton validateButton;

    private String color;
    private String animalType;

    private boolean nameStatus;
    private boolean sizeStatus;
    private boolean vSpeedStatus;
    private boolean hSpeedStatus;

    private AnimalModel model;
    private ZooPanel zooPanel;

    public AddAnimalDialog(AnimalModel model, ZooPanel zooPanel) {
        int dialogX = 500, dialogY = 350;

        this.model = model;
        this.zooPanel = zooPanel;
        // configurations
        this.setModal(true);
        this.setTitle("Add Animal");
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLocation(PrivateGraphicUtils.centerWindow(dialogX, dialogY));
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

    }

    public JPanel createNorthPanel() {
        JPanel animalTypePanel = new JPanel();
        locationLabel = new JLabel("Location: ");
        animalTypesCmb = new JComboBox<>(animalTypes);
        animalTypesCmb.getModel().setSelectedItem("Lion");

        // setting border
        TitledBorder animalChoiceBorder = BorderFactory.createTitledBorder("Choose Animal: ");
        animalTypesCmb.setBorder(animalChoiceBorder);

        // adding to action listener.
        animalTypesCmb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    addAnimalButton.setEnabled(false);
                    String item = (String) e.getItem();
                    switch (item) {
                        case "Lion" -> {
                            uniqueLabel.setText("Scar Count: " + Lion.getDefaultScarCount());
                            locationLabel.setText("Location: " + Lion.getDefaultStartingLocation());
                            animalType = "Lion";
                        }
                        case "Bear" -> {
                            uniqueLabel.setText("Fur Color: " + Bear.getDefaultFurColor());
                            locationLabel.setText("Location: " + Bear.getDefaultStartingLocation());
                            animalType = "Bear";
                        }
                        case "Giraffe" -> {
                            uniqueLabel.setText("Neck Length: " + Giraffe.getDefaultNeckLength());
                            locationLabel.setText("Location: " + Giraffe.getDefaultStartingLocation());
                            animalType = "Giraffe";
                        }
                        case "Turtle" -> {
                            uniqueLabel.setText("Age: " + Turtle.getDefaultAge());
                            locationLabel.setText("Location: " + Turtle.getDefaultStartingLocation());
                            animalType = "Turtle";
                        }
                        case "Elephant" -> {
                            uniqueLabel.setText("Trunk Length: " + Elephant.getDefaultTrunkLength());
                            locationLabel.setText("Location: " + Elephant.getDefaultStartingLocation());
                            animalType = "Elephant";
                        }
                    }

//                    if (color == null) {
//                        color = animalColors[0];
//                    }
                    String path = findAnimalImagePath(animalType, color,DEFAULT_DIRECTION);
                    imageLabel.setIcon(PrivateGraphicUtils.resizeImage(path, 220,180));
                    imageLabel.repaint();
                }
            }
        });


        // adding the combobox to the panel.
        animalTypePanel.add(animalTypesCmb);

        return animalTypePanel;
    }

    public JPanel createSouthPanel() {
        JPanel buttonPanel = new JPanel();

        addAnimalButton = new JButton("Add Animal");
        validateButton = new JButton("Validate");

        // disabling addAnimalButton. will enable after pressing validate - in action listener.
        addAnimalButton.setEnabled(false);

        // adding to action listener.
//        addAnimalButton.addActionListener(listener);
        validateButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 boolean validated;
                 if (model.NameExistInModel(nameTextField.getText())){
                     JOptionPane.showMessageDialog(null, "Name already used, please choose another name",
                             "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else{
                     if (color == null)
                         color = animalColors[0];
                     if (animalType == null)
                         animalType = animalTypes[0];
                     validated = (nameStatus && sizeStatus && vSpeedStatus && hSpeedStatus);
                     addAnimalButton.setEnabled(validated);
//                     else{
//                         JOptionPane.showMessageDialog(null, "Please choose animal and color",
//                                 "Error", JOptionPane.ERROR_MESSAGE);
//                     }
                 }
             }
         });

        addAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String animalName = nameTextField.getText();
                String animalColor = String.valueOf(animalColorsCmb.getSelectedItem());
                int animalSize = Integer.parseInt(sizeTextField.getText());
                int animalVSpeed = Integer.parseInt(vSpeedTextField.getText());
                int animalHSpeed = Integer.parseInt(hSpeedTextField.getText());

                if (model.getModelSize() < AnimalModel.getModelMaxSize()) {
                    Animal animal = null;
                    switch (animalType) {
                        case "Lion" -> animal = new Lion(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                        case "Bear" -> animal = new Bear(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                        case "Giraffe" -> animal = new Giraffe(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                        case "Elephant" -> animal = new Elephant(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                        case "Turtle" -> animal = new Turtle(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                    }
                    animal.setChanges(true);
                    animal.setPan(zooPanel);
                    model.addAnimal(animal);
                    nameTextField.setText("");
                    sizeTextField.setText("");
                    vSpeedTextField.setText("");
                    hSpeedTextField.setText("");
                    animalColorsCmb.setSelectedIndex(0);
                    animalTypesCmb.requestFocusInWindow();
                    if (InfoTableDialog.getIsOpen())
                        zooPanel.getInfoTable().updateTable();
                    zooPanel.manageZoo();
                } else {
                    String message = "You cannot add more than 10 animals";
                    JOptionPane.showMessageDialog(getContentPane().getParent(), message, "Message", JOptionPane.ERROR_MESSAGE, null);
                }
            }
        });

        // adding buttons to button panel.
        buttonPanel.add(validateButton);
        buttonPanel.add(addAnimalButton);
        return buttonPanel;
    }

    public JPanel createEastPanel() {
        JPanel imagePanel = new JPanel(new GridBagLayout());
        String path = findAnimalImagePath(animalTypes[0], animalColors[0],DEFAULT_DIRECTION);
        imageLabel = new JLabel(PrivateGraphicUtils.resizeImage(path,220,180));

        GridBagConstraints gbcImagePanel = new GridBagConstraints();
        gbcImagePanel.gridx = 5;
        gbcImagePanel.gridy = 5;
        gbcImagePanel.insets = new Insets(0, 0, 0, 10);

        TitledBorder border = BorderFactory.createTitledBorder("Picture");
        border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
        imageLabel.setBorder(border);

        imagePanel.add(imageLabel, gbcImagePanel);

        return imagePanel;
    }

    public String getAnimalType() {
        return animalType;
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
        animalColorsCmb.getModel().setSelectedItem("Natural");

        animalColorsCmb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    color = (String) e.getItem();
                    if (animalType == null) {
                        animalType = animalTypes[0];
                    }
                    String path = findAnimalImagePath(animalType, color,DEFAULT_DIRECTION);
                    imageLabel.setIcon(PrivateGraphicUtils.resizeImage(path, 220,180));
                    imageLabel.repaint();
                }
            }
        });
        // setting action commands
        sizeTextField.setActionCommand("SizeTF");

        // setting color for placeholder text.
        sizeTextField.setForeground(Color.GRAY);
        vSpeedTextField.setForeground(Color.GRAY);
        hSpeedTextField.setForeground(Color.GRAY);

        addSpeedInputDocumentListener(vSpeedTextField);
        addSpeedInputDocumentListener(hSpeedTextField);

        // focus listener for input. reset if lost focus & empty.
        addValidRangeFocusListener(sizeTextField, 50, 300);
        addValidRangeFocusListener(vSpeedTextField, 1, 10);
        addValidRangeFocusListener(hSpeedTextField, 1, 10);

        // action listener for input - TODO: DO WE NEED ACTION LISTENERS HERE?
        sizeTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateWeightLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateWeightLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateWeightLabel();
            }

            public void updateWeightLabel() {
                try {
                    String currentSizeText = sizeTextField.getText();
                    addAnimalButton.setEnabled(false);
                    if (currentSizeText.equals("")) {
                        weightLabel.setText("Weight: ");
                    } else {
                        sizeTextField.setForeground(Color.BLACK);
                        sizeStatus = true;
                        int size = Integer.parseInt(currentSizeText);
                        if (size < 50 || size > 300) {
                            throw new NumberFormatException();
                        }
                        weightLabel.setText("Weight: " + getWeightOfSelectedAnimal(size));
                    }
                } catch (NumberFormatException ignored) {
                    sizeTextField.setForeground(Color.RED);
                    sizeStatus = false;
                }
            }
        });

        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkValidName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkValidName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkValidName();
            }

            public void checkValidName() {
                String currentText = nameTextField.getText();
                addAnimalButton.setEnabled(false);
                if (!currentText.matches("[A-Za-z]+")) {
                    nameTextField.setForeground(Color.RED);
                    nameStatus = false;
                } else {
                    nameTextField.setForeground(Color.BLACK);
                    nameStatus = true;
                }
            }
        });

        // create south input panel components
        weightLabel = new JLabel("Weight:");
        uniqueLabel = new JLabel("Scar Count: " + Lion.getDefaultScarCount());
        locationLabel = new JLabel("Location: " + Lion.getDefaultStartingLocation());


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
        gbcSouthInputPanel.insets = new Insets(0, 5, 0, 0);

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

    public double getWeightOfSelectedAnimal(int sizeOfAnimal) {
        double animalWeight = 0;
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
        return animalWeight;
    }

    public String getColor() {
        return color;
    }

    private void addSpeedInputDocumentListener(JTextField speedTextField){
        speedTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkValidInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkValidInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkValidInput();
            }

            public void checkValidInput() {
                try {
                    String currentText = speedTextField.getText();
                    addAnimalButton.setEnabled(false);
                    speedTextField.setForeground(Color.BLACK);
                    if (speedTextField == vSpeedTextField){
                        vSpeedStatus = true;
                    } else hSpeedStatus = true;
                    int speed = Integer.parseInt(currentText);
                    if (speed < 1 || speed > 10){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ignored){
                    speedTextField.setForeground(Color.RED);
                    if (speedTextField == vSpeedTextField){
                        vSpeedStatus = false;
                    } else hSpeedStatus = false;
                }
            }
        });
    }

    /**
     * addValidRangeFocusListener using FocusListener to dynamically change JTextField values.
     * upon focus events.
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
                    validRangeTextField.setText(MIN_RANGE + "-" + MAX_RANGE);
                    validRangeTextField.setForeground(Color.GRAY);
                }
            }
        });
    }
}
