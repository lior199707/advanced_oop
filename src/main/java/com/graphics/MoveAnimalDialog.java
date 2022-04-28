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

public class MoveAnimalDialog extends AnimalDialog {
    // choose coordinates panel
    private static final Dimension DEFAULT_DIMENSION = new Dimension(350,400);
    private JComboBox<String> animalNamesCmb;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel imageLabel;
    private JTextField xTextField;
    private JTextField yTextField;
    private JButton validateButton;
    private JButton moveAnimalButton;
    private JLabel currLocationLabel;

    private boolean xStatus;
    private boolean yStatus;

    public MoveAnimalDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model,zooPanel, DEFAULT_DIMENSION);
        // configurations
        this.setTitle("Move Animals");
        this.createDialog();
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void createDialog() {
        this.getContentPane().add(createNorthPanel(), BorderLayout.NORTH);
        this.getContentPane().add(createCenterPanel(), BorderLayout.CENTER);
        this.getContentPane().add(createSouthPanel(), BorderLayout.SOUTH);
    }

    @Override
    protected JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout());
        animalNamesCmb = new JComboBox<>(getModel().getAnimalNames());
        animalNamesCmb.setSelectedItem(0);
        animalNamesCmb.addItemListener(new AnimalNamesHandler());

        northPanel.add(animalNamesCmb);
        return northPanel;
    }

    @Override
    protected JPanel createWestPanel() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected JPanel createEastPanel() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected JPanel createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();

        xLabel = new JLabel("X: ");
        yLabel = new JLabel("Y: ");
        xTextField = new JTextField("0-800",10);
        yTextField = new JTextField("0-600",10);

        addValidRangeFocusListener(xTextField, Point.getMinXY(), Point.getMaxX());
        addValidRangeFocusListener(yTextField, Point.getMinXY(), Point.getMaxY());

        addCoordinatesInputDocumentListener(xTextField);
        addCoordinatesInputDocumentListener(yTextField);

        xTextField.setForeground(Color.GRAY);
        yTextField.setForeground(Color.GRAY);

        northPanel.add(xLabel);
        northPanel.add(xTextField);
        northPanel.add(yLabel);
        northPanel.add(yTextField);

        JPanel southPanel = new JPanel();

        validateButton = new JButton("Validate");
        moveAnimalButton = new JButton("Move Animal");
        moveAnimalButton.setEnabled(false);
        moveAnimalButton.addActionListener(new MoveAnimalHandler());
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validated = xStatus && yStatus;
                moveAnimalButton.setEnabled(validated);
            }
        });

        southPanel.add(validateButton);
        southPanel.add(moveAnimalButton);

        //title for coordinates panel
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);

        panel.setBorder(PrivateGraphicUtils.createTitledBorder("New Location", TitledBorder.TOP, TitledBorder.CENTER));
        return panel;
    }

    private JPanel createCenterPanel(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        imageLabel = new JLabel();
        imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(getModel().getAnimalModel().get(0)));

        GridBagConstraints centerPanelGbc = new GridBagConstraints();

        //adding the picture label
        setGridBagConstraintPosition(centerPanelGbc, 0,0);
        centerPanel.add(imageLabel, centerPanelGbc);

        centerPanelGbc.anchor = GridBagConstraints.LINE_START;
        Animal animal = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
        currLocationLabel = new JLabel("current location: " + animal.getLocation());

        setGridBagConstraintPosition(centerPanelGbc,0,1);
        centerPanel.add(currLocationLabel, centerPanelGbc);

        imageLabel.setBorder(PrivateGraphicUtils.createTitledBorder("Picture", TitledBorder.BELOW_TOP, TitledBorder.CENTER));

        return centerPanel;
    }

    private void addCoordinatesInputDocumentListener(JTextField coordinatesTextField){
        coordinatesTextField.getDocument().addDocumentListener((IChangeDocument) e -> {
            try {
                String currentText = coordinatesTextField.getText();
                moveAnimalButton.setEnabled(false);

                int coordinate = Integer.parseInt(currentText);
                if (coordinatesTextField == xTextField) {
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
            } catch (NumberFormatException ignored){
                if (coordinatesTextField == xTextField){
                    setValidTextField(coordinatesTextField, xStatus = false);
                } else {
                    setValidTextField(coordinatesTextField, yStatus = false);
                }
            }
        });
    }


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

    private class MoveAnimalHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int x = Integer.parseInt(xTextField.getText());
            int y = Integer.parseInt(yTextField.getText());
            Animal animal = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
            if (animal.move(new Point(x,y)) != 0){
                currLocationLabel.setText("current location: " + animal.getLocation());
                animal.setChanges(true);
                animal.checkEatFood(getZooPanel().getFood());
                getZooPanel().manageZoo();
                if (getModel().getChangesState()){
                    getModel().setChangesState(false);
                    refreshUI();
                }

                xTextField.setText("0-800");
                yTextField.setText("0-600");
                xTextField.setForeground(Color.GRAY);
                yTextField.setForeground(Color.GRAY);
            } else {
                String message = "Animal did not move!";
                PrivateGraphicUtils.popInformationDialog(null, message);
//                try {
//                    String message = "Animal did not move!";
//                    throw new PrivateGraphicUtils.InformationDialogException(getContentPane(), message);
//                } catch (PrivateGraphicUtils.InformationDialogException ignored) {}
            }
        }
    }


    public void refreshUI(){
        animalNamesCmb.setModel(new DefaultComboBoxModel<>(getModel().getAnimalNames()));
        // if the moved animal was eaten, set the current selected index animal image
        Animal current = getModel().getAnimalModel().get(animalNamesCmb.getSelectedIndex());
        imageLabel.setIcon(PrivateGraphicUtils.setAnimalImageIcon(current));

    }
}