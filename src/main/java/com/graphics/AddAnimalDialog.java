package com.graphics;

import com.animals.*;
import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static com.privateutil.PrivateGraphicUtils.findAnimalImagePath;

public class AddAnimalDialog extends AnimalDialog {
    private static final int DEFAULT_DIRECTION = 1;
    private static final Dimension DEFAULT_DIMENSION = new Dimension(500, 350);
    private final static String INIT_ANIMAL_TYPE = "Lion";
    private final static String INIT_ANIMAL_COLOR = "Natural";
    private final static String INIT_ANIMAL_SHORT_PATH = "lio";
    private final static double[] coefficientArr = {
            Lion.getSizeCoefficient(),
            Bear.getSizeCoefficient(),
            Giraffe.getSizeCoefficient(),
            Elephant.getSizeCoefficient(),
            Turtle.getSizeCoefficient()
    };
    private final String[] animalTypes = {"Lion", "Bear", "Giraffe", "Elephant", "Turtle"};
    private final String[] animalShortPaths = {"lio", "bea", "grf", "elf", "trt"};
    private final String[] animalColors = {"Natural", "Red", "Blue"};

    private final JComboBox<String> animalTypesCmb;
    private final JComboBox<String> animalColorsCmb;

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

    private String animalColor;
    private String animalType;
    private String animalShortPathName;

    private boolean nameStatus;
    private boolean sizeStatus;
    private boolean vSpeedStatus;
    private boolean hSpeedStatus;

    public AddAnimalDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model, zooPanel, DEFAULT_DIMENSION);
        // configurations

        animalTypesCmb = new JComboBox<>(animalTypes);
        animalColorsCmb = new JComboBox<>(animalColors);
        animalType = INIT_ANIMAL_TYPE;
        animalColor = INIT_ANIMAL_COLOR;
        animalShortPathName = INIT_ANIMAL_SHORT_PATH;

        this.setTitle("Add Animal");
        this.createDialog();
        this.setVisible(true);
        this.pack();
    }

    // setting up panels on given frame.
    @Override
    public void createDialog() {
        this.getContentPane().add(createNorthPanel(), BorderLayout.NORTH);
        this.getContentPane().add(createEastPanel(), BorderLayout.EAST);
        this.getContentPane().add(createWestPanel(), BorderLayout.WEST);
        this.getContentPane().add(createSouthPanel(), BorderLayout.SOUTH);
    }

    @Override
    protected JPanel createNorthPanel() {
        JPanel animalTypePanel = new JPanel();
        locationLabel = new JLabel("Location: ");

        // setting border
        animalTypesCmb.setBorder(PrivateGraphicUtils.createTitledBorder("Choose Animal:",
                TitledBorder.TOP, TitledBorder.CENTER));

        // adding item listener.
        animalTypesCmb.addItemListener(new AnimalTypesHandler());

        // adding the combobox to the panel.
        animalTypePanel.add(animalTypesCmb);

        return animalTypePanel;
    }


    // creating west panel containing user input & dynamic fields.
    @Override
    protected JPanel createWestPanel() {
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

        animalColorsCmb.addItemListener(new AnimalColorHandler());

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
        sizeTextField.getDocument().addDocumentListener(new IChangeDocument() {
            @Override
            public void changeDocument(DocumentEvent e) {
                try {
                    String currentSizeText = sizeTextField.getText();
                    addAnimalButton.setEnabled(false);
                    if (currentSizeText.equals("")) {
                        weightLabel.setText("Weight: ");
                    } else {
                        setValidTextField(sizeTextField, sizeStatus = true);
                        int size = Integer.parseInt(currentSizeText);
                        if (size < 50 || size > 300) {
                            throw new NumberFormatException();
                        }
                        weightLabel.setText("Weight: " + getWeightOfSelectedAnimal(size));
                    }
                } catch (NumberFormatException ignored) {
                    setValidTextField(sizeTextField, sizeStatus = false);
                }
            }
        });

        nameTextField.getDocument().addDocumentListener(new IChangeDocument() {
            @Override
            public void changeDocument(DocumentEvent e) {
                String currentText = nameTextField.getText();
                addAnimalButton.setEnabled(false);
                if (currentText.matches("[A-Za-z]+")) {
                    setValidTextField(nameTextField, nameStatus = true);
                } else {
                    setValidTextField(nameTextField, nameStatus = false);
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
        setGridBagConstraintPosition(gbcNorthInputPanel,0,0);
        northInputPanel.add(nameLabel, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,0,1);
        northInputPanel.add(sizeLabel, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,0,2);
        northInputPanel.add(vSpeedLabel, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,0,3);
        northInputPanel.add(hSpeedLabel, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,0,4);
        northInputPanel.add(colorLabel, gbcNorthInputPanel);

        // input constraints.
        setGridBagConstraintPosition(gbcNorthInputPanel,1,0);
        northInputPanel.add(nameTextField, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,1,1);
        northInputPanel.add(sizeTextField, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,1,2);
        northInputPanel.add(vSpeedTextField, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,1,3);
        northInputPanel.add(hSpeedTextField, gbcNorthInputPanel);

        setGridBagConstraintPosition(gbcNorthInputPanel,1,4);
        northInputPanel.add(animalColorsCmb, gbcNorthInputPanel);

        // setting layout constraints for south panel.
        GridBagConstraints gbcSouthInputPanel = new GridBagConstraints();

        gbcSouthInputPanel.anchor = GridBagConstraints.LINE_START;
        gbcSouthInputPanel.weightx = 1;
        gbcSouthInputPanel.insets = new Insets(0, 5, 0, 0);

        setGridBagConstraintPosition(gbcSouthInputPanel,0,5);
        southInputPanel.add(weightLabel, gbcSouthInputPanel);

        setGridBagConstraintPosition(gbcSouthInputPanel,0,6);
        southInputPanel.add(uniqueLabel, gbcSouthInputPanel);

        setGridBagConstraintPosition(gbcSouthInputPanel,0,7);
        southInputPanel.add(locationLabel, gbcSouthInputPanel);

        // setting up a titled border.
//        TitledBorder northInputBorder = BorderFactory.createTitledBorder("Input: ");
        northInputPanel.setBorder(PrivateGraphicUtils.createTitledBorder("Input:", TitledBorder.CENTER, TitledBorder.LEFT));

        // positioning the panels in north & south
        inputPanel.add(northInputPanel, BorderLayout.NORTH);
        inputPanel.add(southInputPanel, BorderLayout.SOUTH);

        // return the input panel.
        return inputPanel;
    }

    @Override
    protected JPanel createEastPanel() {
        JPanel imagePanel = new JPanel(new GridBagLayout());
        String path = findAnimalImagePath(INIT_ANIMAL_TYPE,INIT_ANIMAL_SHORT_PATH, INIT_ANIMAL_COLOR, DEFAULT_DIRECTION);
        imageLabel = new JLabel(PrivateGraphicUtils.resizeImage(path, 220, 180));

        GridBagConstraints gbcImagePanel = new GridBagConstraints();
        gbcImagePanel.insets = new Insets(0, 0, 0, 10);
        setGridBagConstraintPosition(gbcImagePanel,5,5);

        imageLabel.setBorder(PrivateGraphicUtils.createTitledBorder("Picture",TitledBorder.BELOW_TOP, TitledBorder.CENTER));
        imagePanel.add(imageLabel, gbcImagePanel);

        return imagePanel;
    }

    @Override
    protected JPanel createSouthPanel() {
        JPanel buttonPanel = new JPanel();

        addAnimalButton = new JButton("Add Animal");
        validateButton = new JButton("Validate");

        // disabling addAnimalButton. will enable after pressing validate - in action listener.
        addAnimalButton.setEnabled(false);

        // adding to action listener.
        validateButton.addActionListener(validationHandler -> {
            boolean validated;
            if (getModel().containsAnimalName(nameTextField.getText())) {
                try {
                    String message = "Name already used, please choose another name";
                    throw new PrivateGraphicUtils.ErrorDialogException(getContentPane(), message);
                } catch (PrivateGraphicUtils.ErrorDialogException ignored) {}
            } else {
                validated = (nameStatus && sizeStatus && vSpeedStatus && hSpeedStatus);
                addAnimalButton.setEnabled(validated);
            }
        });

        addAnimalButton.addActionListener(new AddAnimalHandler());

        // adding buttons to button panel.
        buttonPanel.add(validateButton);
        buttonPanel.add(addAnimalButton);
        return buttonPanel;
    }

    public double getWeightOfSelectedAnimal(int sizeOfAnimal) {
        return sizeOfAnimal * coefficientArr[animalTypesCmb.getSelectedIndex()];
    }

    private void addSpeedInputDocumentListener(JTextField speedTextField) {
        speedTextField.getDocument().addDocumentListener(new IChangeDocument() {
            @Override
            public void changeDocument(DocumentEvent e) {
                try {
                    addAnimalButton.setEnabled(false);
                    String currentText = speedTextField.getText();
                    if (speedTextField == vSpeedTextField){
                        setValidTextField(speedTextField, vSpeedStatus = true);
                    } else {
                        setValidTextField(speedTextField, hSpeedStatus = true);
                    }
                    int speed = Integer.parseInt(currentText);
                    if (speed < 1 || speed > 10){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ignored){
                    if (speedTextField == vSpeedTextField){
                        setValidTextField(speedTextField, vSpeedStatus = false);
                    } else {
                        setValidTextField(speedTextField, hSpeedStatus = false);
                    }
                }
            }
        });
    }


    private class AnimalTypesHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                addAnimalButton.setEnabled(false);
                String item = (String) e.getItem();
                switch (item) {
                    case "Lion" -> {
                        uniqueLabel.setText("Scar Count: " + Lion.getDefaultScarCount());
                        locationLabel.setText("Location: " + Lion.getDefaultStartingLocation());
                        animalType = animalTypes[0];
                        animalShortPathName = animalShortPaths[0];
                    }
                    case "Bear" -> {
                        uniqueLabel.setText("Fur Color: " + Bear.getDefaultFurColor());
                        locationLabel.setText("Location: " + Bear.getDefaultStartingLocation());
                        animalType = animalTypes[1];
                        animalShortPathName = animalShortPaths[1];
                    }
                    case "Giraffe" -> {
                        uniqueLabel.setText("Neck Length: " + Giraffe.getDefaultNeckLength());
                        locationLabel.setText("Location: " + Giraffe.getDefaultStartingLocation());
                        animalType = animalTypes[2];
                        animalShortPathName = animalShortPaths[2];
                    }
                    case "Turtle" -> {
                        uniqueLabel.setText("Age: " + Turtle.getDefaultAge());
                        locationLabel.setText("Location: " + Turtle.getDefaultStartingLocation());
                        animalType = animalTypes[3];
                        animalShortPathName = animalShortPaths[3];
                    }
                    case "Elephant" -> {
                        uniqueLabel.setText("Trunk Length: " + Elephant.getDefaultTrunkLength());
                        locationLabel.setText("Location: " + Elephant.getDefaultStartingLocation());
                        animalType = animalTypes[4];
                        animalShortPathName = animalShortPaths[4];
                    }
                }
                String path = findAnimalImagePath(animalType, animalShortPathName, animalColor, DEFAULT_DIRECTION);
                imageLabel.setIcon(PrivateGraphicUtils.resizeImage(path, 220, 180));
                imageLabel.repaint();
            }
        }
    }

    private class AddAnimalHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String animalName = nameTextField.getText();
            String animalColor = String.valueOf(animalColorsCmb.getSelectedItem());
            int animalSize = Integer.parseInt(sizeTextField.getText());
            int animalVSpeed = Integer.parseInt(vSpeedTextField.getText());
            int animalHSpeed = Integer.parseInt(hSpeedTextField.getText());

            if (getModel().getAnimalModelSize() < AnimalModel.getModelMaxSize()) {
                Animal animal = null;
                switch (animalType) {
                    case "Lion" -> animal = new Lion(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                    case "Bear" -> animal = new Bear(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                    case "Giraffe" -> animal = new Giraffe(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                    case "Elephant" -> animal = new Elephant(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                    case "Turtle" -> animal = new Turtle(animalName, animalSize, animalHSpeed, animalVSpeed, animalColor);
                }

                assert animal != null;
                animal.setChanges(true);
                animal.setPan(getZooPanel());
                getModel().addAnimal(animal);
                nameTextField.setText("");
                sizeTextField.setText("50-300");
                vSpeedTextField.setText("1-10");
                hSpeedTextField.setText("1-10");
                sizeTextField.setForeground(Color.GRAY);
                vSpeedTextField.setForeground(Color.GRAY);
                hSpeedTextField.setForeground(Color.GRAY);

                animalColorsCmb.setSelectedIndex(0);
                animalTypesCmb.requestFocusInWindow();
                if (InfoTableDialog.getIsOpen())
                    getZooPanel().getInfoTable().updateTable();

                getZooPanel().manageZoo();
            } else {
                try {
                    String message = "You cannot add more than 10 animals";
                    throw new PrivateGraphicUtils.ErrorDialogException(getContentPane().getParent(), message);
                } catch (PrivateGraphicUtils.ErrorDialogException ignored) {}
            }
        }
    }

    private class AnimalColorHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                animalColor = (String) e.getItem();
                String path = findAnimalImagePath(animalType, animalShortPathName, animalColor, DEFAULT_DIRECTION);
                imageLabel.setIcon(PrivateGraphicUtils.resizeImage(path, 220, 180));
                imageLabel.repaint();
            }
        }
    }
}
