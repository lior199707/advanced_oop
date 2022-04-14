package com.graphics;

import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MoveAnimalDialog extends JDialog {
    //name string will be given in the constructor
    //private final MoveAnimalDialogListener listener;
    private JComboBox<String> animalsNames;
    // choose coordinates panel
    private JComboBox<String> animalNames;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel currAnimalXLocationLabel;
    private  JLabel currAnimalYocationLable;
    private JTextField xTextField;
    private JTextField yTextField;
    private JButton validateButton;
    private JButton moveAnimalButton;
    private BufferedImage picture;

    public MoveAnimalDialog(final ArrayList<String> zooAnimalsNames) {
        int dialogX = 310, dialogY = 420;

        // configurations
        this.setModal(true);
        this.setTitle("Move Animals");
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

        this.createDialog(this, zooAnimalsNames);
        this.setVisible(true);
        this.pack();

        //listener = new AddAnimalDialogListener();

    }

    private JPanel createSouthPanel() {
        GridBagConstraints southPanelGbl = new GridBagConstraints();
        xLabel = new JLabel("X: ");
        xLabel.setFont(new Font("Default", Font.PLAIN, 20));
        yLabel = new JLabel("   Y: ");
        yLabel.setFont(new Font("Default", Font.PLAIN, 20));
        xTextField = new JTextField(10);
        yTextField = new JTextField(10);
        validateButton = new JButton("Validate");
        //validateButton.addActionListener(listener);
        moveAnimalButton = new JButton("Move Animals");
        //moveAnimalButton.addActionListener(listener);
        JPanel southPanel = new JPanel(new GridBagLayout());
        //southPanelGbl.weightx = 0;
        //southPanelGbl.weighty = 0;
        southPanelGbl.fill = GridBagConstraints.HORIZONTAL;
        //adding x label
        southPanelGbl.gridy = 0;
        southPanelGbl.gridx = 0;
        southPanelGbl.insets = new Insets(0, 0, 5, 0);
        southPanel.add(xLabel, southPanelGbl);
        //adding x text field
        southPanelGbl.gridx = 1;
        southPanelGbl.gridy = 0;
        southPanelGbl.gridwidth = 4;
        southPanelGbl.insets = new Insets(0, 0, 0, 0);
        southPanel.add(xTextField, southPanelGbl);
        //adding y label
        southPanelGbl.insets = new Insets(0, 0, 5, 0);
        southPanelGbl.gridx = 5;
        southPanelGbl.gridy = 0;
        southPanelGbl.gridwidth = 1;
        southPanel.add(yLabel, southPanelGbl);
        //adding y text field
        southPanelGbl.insets = new Insets(0, 0, 0, 0);
        southPanelGbl.gridx = 6;
        southPanelGbl.gridy = 0;
        southPanelGbl.gridwidth = 4;
        southPanel.add(yTextField, southPanelGbl);
        //adding buttons panel
        JPanel buttonsPanel = createButtonsPanel();
        southPanelGbl.gridx = 0;
        southPanelGbl.gridy = 1;
        southPanelGbl.gridwidth = 10;
        //10
        southPanel.add(buttonsPanel, southPanelGbl);
        //title for coordinates panel
        Border bor = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title = BorderFactory.createTitledBorder(bor, "New Location");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitleFont(new Font("Default", Font.PLAIN, 10));
        southPanel.setBorder(title);
        return southPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        JLabel vlaidateButtonText = new JLabel("Validate");
        vlaidateButtonText.setFont(new Font("Default", Font.PLAIN, 10));
        validateButton = new JButton(vlaidateButtonText.getText());
        JLabel moveButtonText = new JLabel("Move Animals");
        moveButtonText.setFont(new Font("Default", Font.PLAIN, 10));
        JButton moveButton = new JButton(moveButtonText.getText());
        buttonsPanel.add(validateButton);
        buttonsPanel.add(moveAnimalButton);
        return buttonsPanel;

    }

    private JPanel createNorthPanel(final ArrayList<String> zooAnimalsNames) {
        JPanel northPanel = new JPanel(new GridLayout());
        animalNames = new JComboBox<String>(zooAnimalsNames.toArray(new String[0]));
        northPanel.add(animalNames);
        return northPanel;
    }

    private JPanel createCenterPanel(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(PrivateGraphicUtils.createImageIcon(PrivateGraphicUtils.findImagePath("Lion","Natural")));

        GridBagConstraints centerPanelGbc = new GridBagConstraints();
        //default
        centerPanelGbc.weightx = 0;
        centerPanelGbc.weighty = 0;
        centerPanelGbc.fill = GridBagConstraints.HORIZONTAL;
        //adding the picture label
        centerPanelGbc.gridx = 0;
        centerPanelGbc.gridy = 0;
        centerPanel.add(imageLabel, centerPanelGbc);
        //adding current location for x coordinate
        currAnimalXLocationLabel = new JLabel("current X:");
        currAnimalXLocationLabel.setFont(new Font("Default", Font.BOLD, 10));
        centerPanelGbc.gridx = 0;
        centerPanelGbc.gridy = 1;
        centerPanelGbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(currAnimalXLocationLabel, centerPanelGbc);
        //adding current location for y coordinate
        currAnimalYocationLable = new JLabel("current Y:");
        currAnimalYocationLable.setFont(new Font("Default", Font.BOLD, 10));
        centerPanelGbc.gridx = 0;
        centerPanelGbc.gridy = 2;
        centerPanel.add(currAnimalYocationLable, centerPanelGbc);

        TitledBorder border = BorderFactory.createTitledBorder("Picture");
        border.setTitlePosition(TitledBorder.BELOW_TOP);
        imageLabel.setBorder(border);

        return centerPanel;
    }

//    private static MoveAnimalDialog instance = null;
//    JFrame frame;
//    JPanel panel;
//
//    private MoveAnimalDialog(){
//        frame = new JFrame("Move Animal");
//        panel = new JPanel();
//        JLabel label = new JLabel("wejjkwejr");
//        frame.add(panel);
//        frame.add(label);
//
//
//        frame.setSize(100,100);
//        frame.setVisible(true);


    public void createDialog(JDialog dialog, final ArrayList<String> zooAnimalsNames) {
        dialog.getContentPane().add(createSouthPanel(), BorderLayout.SOUTH);
        dialog.getContentPane().add(createNorthPanel(zooAnimalsNames), BorderLayout.NORTH);
        dialog.getContentPane().add(createCenterPanel(), BorderLayout.CENTER);
    }
}

//    public static MoveAnimalDialog getInstance(){
//        if (instance == null) {
//            instance = new MoveAnimalDialog();
//        }
//        return instance;
//    }

