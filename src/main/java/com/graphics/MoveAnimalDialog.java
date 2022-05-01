package com.graphics;

import com.animals.Animal;
import com.mobility.Point;
import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * MoveAnimalDialog represents a dialog for moving animals in the Zoo.
 * users can select animals from a combobox, input coordinates and move the animals to the new coordinates if in boundaries.
 * the ui will present an appropriate image (based on animal type and color) dynamically.
 * @see com.graphics.AnimalDialog
 */
public class MoveAnimalDialog extends AnimalDialog {
    /**
     * default Dimension object with width and height.
     */
    private static final Dimension DEFAULT_DIMENSION = new Dimension(350,400);
    /**
     * combobox of animal names - taken from the AnimalModel.
     */
    private JComboBox<String> animalNamesCmb;
    /**
     * JLabel object indicating animal image, changed dynamically.
     */
    private JLabel imageLabel;
    /**
     * JTextField to input the x coordinate.
     */
    private JTextField xTextField;
    /**
     * JTextField to input the y coordinate.
     */
    private JTextField yTextField;
    /**
     * JButton object indicating the validate button, pressing it will validate the user input before enabling the move animal button.
     */
    private JButton validateButton;
     /**
     * JButton object indicating the move animal button, pressing it will move the animal to the given input coordinates.
     */
    private JButton moveAnimalButton;
    /**
     * JLabel object indicating the current location coordinates of a selected animal.
     */
    private JLabel currLocationLabel;
    /**
     * boolean representation of the x text field validity state.
     */
    private boolean xStatus;
    /**
     * boolean representation of the y text field validity state.
     */
    private boolean yStatus;

    /**
     * MoveAnimalDialog constructor
     * @param model AnimalModel object, the animal container.
     * @param zooPanel ZooPanel object, the zoo panel.
     * @see com.graphics.AnimalDialog
     */
    public MoveAnimalDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model,zooPanel, DEFAULT_DIMENSION);
        // configurations
        this.setTitle("Move Animals");
        this.createDialog();
        this.setVisible(true);
        this.pack();
    }

    /**
     * createDialog adding directional panels to the MoveAnimalDialog
     * @see com.graphics.AnimalDialog
     */
    @Override
    public void createDialog() {
        this.getContentPane().add(createNorthPanel(), BorderLayout.NORTH);
        this.getContentPane().add(createCenterPanel(), BorderLayout.CENTER);
        this.getContentPane().add(createSouthPanel(), BorderLayout.SOUTH);
    }

    /**
     * createNorthPanel will add items to the north panel.
     * adding animal name combo box to the north panel.
     * @see com.graphics.AnimalDialog
     * @return JPanel object of the north panel.
     */
    @Override
    protected JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout());
        animalNamesCmb = new JComboBox<>(getModel().getAnimalNames());
        animalNamesCmb.setSelectedItem(0);
        animalNamesCmb.addItemListener(new AnimalNamesHandler());

        northPanel.add(animalNamesCmb);
        return northPanel;
    }

    /**
     * createWestPanel does nothing.
     * @see com.graphics.AnimalDialog
     */
    @Override
    protected JPanel createWestPanel() {
        throw new UnsupportedOperationException();
    }

    /**
     * createEastPanel does nothing.
     * @see com.graphics.AnimalDialog
     */
    @Override
    protected JPanel createEastPanel() {
        throw new UnsupportedOperationException();
    }

    /**
     * createSouthPanel will add items to the south panel.
     * adding the coordinates x and y text fields to a northern panel.
     * adding the move animal and validate buttons to a southern panel.
     * adding the mentioned panels above to the south panel of the dialog.
     * @see com.graphics.AnimalDialog
     * @return JPanel object of the south panel.
     */
    @Override
    protected JPanel createSouthPanel() {
        // initializing panels.
        JPanel panel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        // setting layout
        panel.setLayout(new BorderLayout());

        // create north panel components.
        JLabel xLabel = new JLabel("X: ");
        JLabel yLabel = new JLabel("Y: ");
        xTextField = new JTextField("0-800",10);
        yTextField = new JTextField("0-600",10);

        xTextField.setForeground(Color.GRAY);
        yTextField.setForeground(Color.GRAY);

        // adding to listeners
        addValidRangeFocusListener(xTextField, Point.getMinXY(), Point.getMaxX());
        addValidRangeFocusListener(yTextField, Point.getMinXY(), Point.getMaxY());

        addCoordinatesInputDocumentListener(xTextField);
        addCoordinatesInputDocumentListener(yTextField);

        // adding components to the north panel. note it's using FlowLayout by default.
        northPanel.add(xLabel);
        northPanel.add(xTextField);
        northPanel.add(yLabel);
        northPanel.add(yTextField);

        // create south panel components.
        validateButton = new JButton("Validate");
        moveAnimalButton = new JButton("Move Animal");
        moveAnimalButton.setEnabled(false);

        // adding to listeners
        moveAnimalButton.addActionListener(new MoveAnimalHandler());
        validateButton.addActionListener(validationHandler -> {
            boolean validated;
            validated = xStatus && yStatus;
            // enables the move animal button only if both values are true.
            if (!validated){
                try {
                    throw new PrivateGraphicUtils.ErrorDialogException(this,"Invalid input!\nPlease ensure there are no red flags before validating the input.");
                } catch (PrivateGraphicUtils.ErrorDialogException ignored) { }
            }
            moveAnimalButton.setEnabled(validated);
        });

        southPanel.add(validateButton);
        southPanel.add(moveAnimalButton);

        //title for coordinates panel
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);

        panel.setBorder(PrivateGraphicUtils.createTitledBorder("New Location", TitledBorder.TOP, TitledBorder.CENTER));

        return panel;
    }

    /**
     * utility method to create a center panel.
     * adding image and current location labels to the panel.
     * @return JPanel object of the center panel.
     */
    private JPanel createCenterPanel(){
        JPanel centerPanel = new JPanel();

        imageLabel = new JLabel();
        imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(getModel().getAnimalModel().get(0)));

        GridBagConstraints centerPanelGbc = new GridBagConstraints();
        centerPanelGbc.anchor = GridBagConstraints.LINE_START;

        // setting up the image label.
        setGridBagConstraintPosition(centerPanelGbc, 0,0);
        imageLabel.setBorder(PrivateGraphicUtils.createTitledBorder("Picture", TitledBorder.BELOW_TOP, TitledBorder.CENTER));
        centerPanel.add(imageLabel, centerPanelGbc);

        // setting up the current location label.
        setGridBagConstraintPosition(centerPanelGbc,0,1);
        Animal animal = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
        currLocationLabel = new JLabel("current location: " + animal.getLocation());
        centerPanel.add(currLocationLabel, centerPanelGbc);

        return centerPanel;
    }

    /**
     * utility method using a document listener to listen for document events on the coordinates
     * text fields x and y.
     * @param coordinatesTextField JTextField object, either xTextField or yTextField.
     */
    private void addCoordinatesInputDocumentListener(JTextField coordinatesTextField){
        coordinatesTextField.getDocument().addDocumentListener((IChangeDocument) e -> {
            try {
                // getting the current input.
                String currentText = coordinatesTextField.getText();
                moveAnimalButton.setEnabled(false);

                int coordinate = Integer.parseInt(currentText);
                // if the coordinate is parsed successfully it will check if the coordinate are in range.
                if (coordinatesTextField == xTextField) {
                    // if so, the text field foreground will be set to black & the status will be set to true.
                    setValidTextField(coordinatesTextField, xStatus = true);
                    if (coordinate < Point.getMinXY() || coordinate > Point.getMaxX()) {
                        throw new NumberFormatException();
                    }
                } else {
                    setValidTextField(coordinatesTextField, yStatus = true);
                    if (coordinate < Point.getMinXY() || coordinate > Point.getMaxY()) {
                        throw new NumberFormatException();
                    }
                }
                // otherwise, a NumberFormatException will be thrown and turn the foreground of the
                // text fields to red, and its status to false.
            } catch (NumberFormatException ignored){
                if (coordinatesTextField == xTextField){
                    setValidTextField(coordinatesTextField, xStatus = false);
                } else {
                    setValidTextField(coordinatesTextField, yStatus = false);
                }
            }
        });
    }


    /**
     * AnimalNamesHandler is a utility class implementing ItemListener.
     * it is used to update the current location label and the image label upon selection of a new animal.
     * @see ItemListener
     */
    private class AnimalNamesHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED){
                Animal animal = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
                imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(animal));
                currLocationLabel.setText("current location: " + animal.getLocation());
            }
        }
    }

    /**
     * MoveAnimalHandler is a utility class implementing ActionListener.
     * it is used to listen to move animal button event.
     * if the distance measured between current location and the new location is 0, the animal will not move
     * and an appropriate dialog will appear.
     * otherwise, the animal will move to the new location and a set of actions will take place to update the UI.
     * upon moving an animal conditionalFoodEating will attempt to eat Food if existed.
     * @see ActionListener
     */
    private class MoveAnimalHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int x = Integer.parseInt(xTextField.getText());
            int y = Integer.parseInt(yTextField.getText());
            Animal animal = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
            if (animal.move(new Point(x,y)) != 0){
                // updating animal coordChanged to true.
                animal.setChanges(true);
                // updating the current location label.
                currLocationLabel.setText("current location: " + animal.getLocation());
                // attempting to eat food.
                animal.conditionalFoodEating(getZooPanel().getFood());
                getZooPanel().manageZoo();
                // if the model was changed via this movement in manageZoo it will refresh the UI.
                if (getModel().getChangesState()){
                    getModel().setChangesState(false);
                    refreshUI();
                }

                // resetting fields to default placeholders.
                xTextField.setText("0-800");
                yTextField.setText("0-600");
                xTextField.setForeground(Color.GRAY);
                yTextField.setForeground(Color.GRAY);
            } else {
                String message = "Animal did not move!";
                PrivateGraphicUtils.popInformationDialog(null, message);
            }
        }
    }


    /**
     * refreshes the combobox, image and current location labels upon changes.
     * generally used if the moving animal was eaten.
     */
    public void refreshUI(){
        // updating the combobox with the current names in the model.
        animalNamesCmb.setModel(new DefaultComboBoxModel<>(getModel().getAnimalNames()));
        // if the moved animal was eaten, set the current selected index animal image
        Animal current = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
        currLocationLabel.setText("current location: " + current.getLocation());
        imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(current));
    }
}