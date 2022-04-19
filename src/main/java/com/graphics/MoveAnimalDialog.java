package com.graphics;

import com.mobility.Point;
import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class MoveAnimalDialog extends JDialog {
    // choose coordinates panel
    private JComboBox<String> animalNames;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel imageLabel;
    private JTextField xTextField;
    private JTextField yTextField;
    private JButton validateButton;
    private JButton moveAnimalButton;
    private JLabel currLocationLabel;
    private AnimalModel model;
    private boolean xStatus;
    private boolean yStatus;
    private ZooPanel zooPanel;


    public MoveAnimalDialog(AnimalModel model, ZooPanel zooPanel) {
        int dialogX = 350, dialogY = 400;
        this.model = model;
        this.zooPanel = zooPanel;

        // configurations
        this.setModal(true);
        this.setTitle("Move Animals");
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLocation(PrivateGraphicUtils.centerWindow(dialogX, dialogY));
        this.setSize(new Dimension(dialogX, dialogY));
        this.setResizable(true);

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

    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout());
        animalNames = new JComboBox<>(model.getAnimalNames());
        animalNames.setSelectedItem(0);
        animalNames.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = animalNames.getSelectedIndex();
                    imageLabel.setIcon(PrivateGraphicUtils.resizeImage(model.getModel().get(index)));
                    currLocationLabel.setText("current location: " + model.getModel().get(animalNames.getSelectedIndex()).getLocation());
                }
            }
        });

        northPanel.add(animalNames);
        return northPanel;
    }

    private JPanel createSouthPanel() {
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
        moveAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(xTextField.getText());
                int y = Integer.parseInt(yTextField.getText());
                model.getModel().get(animalNames.getSelectedIndex()).move(new Point(x,y));
                currLocationLabel.setText("current location: " + model.getModel().get(animalNames.getSelectedIndex()).getLocation());
                zooPanel.manageZoo();
            }
        });

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validated;
                validated = (xStatus && yStatus);
                moveAnimalButton.setEnabled(validated);
            }
        });

        southPanel.add(validateButton);
        southPanel.add(moveAnimalButton);

        //title for coordinates panel
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);

//        Border bor = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title = BorderFactory.createTitledBorder("New Location");
        title.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(title);
        return panel;
    }


    private void addCoordinatesInputDocumentListener(JTextField coordinatesTextField){
        coordinatesTextField.getDocument().addDocumentListener(new DocumentListener() {
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
                    String currentText = coordinatesTextField.getText();
                    moveAnimalButton.setEnabled(false);
                    coordinatesTextField.setForeground(Color.BLACK);

                    int coordinate = Integer.parseInt(currentText);
                    if (coordinatesTextField == xTextField) {
                        xStatus = true;
                        if (coordinate < Point.getMinXY() || coordinate > Point.getMaxX()) {
                            xStatus = false;
                            throw new NumberFormatException();
                        }
                    } else {
                        yStatus = true;
                        if (coordinate < Point.getMinXY() || coordinate > Point.getMaxY()) {
                            yStatus = false;
                            throw new NumberFormatException();
                        }
                    }
                } catch (NumberFormatException ignored){
                    coordinatesTextField.setForeground(Color.RED);
                }
            }
        });
    }

    private JPanel createCenterPanel(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        imageLabel = new JLabel();
        imageLabel.setIcon(PrivateGraphicUtils.resizeImage(model.getModel().get(0)));

        GridBagConstraints centerPanelGbc = new GridBagConstraints();

        //adding the picture label
        centerPanelGbc.gridx = 0;
        centerPanelGbc.gridy = 0;
        centerPanel.add(imageLabel, centerPanelGbc);

        centerPanelGbc.gridx = 0;
        centerPanelGbc.gridy = 1;
        centerPanelGbc.anchor = GridBagConstraints.LINE_START;
        currLocationLabel = new JLabel("current location: " + model.getModel().get(animalNames.getSelectedIndex()).getLocation());

        centerPanel.add(currLocationLabel, centerPanelGbc);

        TitledBorder border = BorderFactory.createTitledBorder("Picture");
        border.setTitlePosition(TitledBorder.BELOW_TOP);
        imageLabel.setBorder(border);

        return centerPanel;
    }



    public void createDialog(JDialog dialog){
        dialog.getContentPane().add(createNorthPanel(), BorderLayout.NORTH);
        dialog.getContentPane().add(createCenterPanel(), BorderLayout.CENTER);
        dialog.getContentPane().add(createSouthPanel(), BorderLayout.SOUTH);
    }
    
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