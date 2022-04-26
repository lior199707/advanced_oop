package com.graphics;

import com.privateutil.PrivateGraphicUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class AnimalDialog extends JDialog {
    private final ZooPanel zooPanel;
    private final AnimalModel model;

    public AnimalDialog(AnimalModel model, ZooPanel zooPanel, Dimension dimension){
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLocation(PrivateGraphicUtils.centerWindow((int) dimension.getWidth(), (int) dimension.getHeight()));
        this.addWindowListener(new AnimalDialogWindowAdapter());
        this.setSize(dimension);
        this.setResizable(false);

        this.model = model;
        this.zooPanel = zooPanel;
    }

    protected abstract JPanel createNorthPanel();
    protected abstract JPanel createWestPanel();
    protected abstract JPanel createEastPanel();
    protected abstract JPanel createSouthPanel();
    protected abstract void createDialog();

    protected void addValidRangeFocusListener(JTextField validRangeTextField,int MIN_RANGE, int MAX_RANGE) {
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

    private class AnimalDialogWindowAdapter extends WindowAdapter {
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
    }

    protected void setGridBagConstraintPosition(GridBagConstraints gbc, int gridx, int gridy){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
    }

    public void setValidTextField(JTextField textField, boolean state){
        if (state)
            textField.setForeground(Color.BLACK);
        else
            textField.setForeground(Color.RED);
    }

    public AnimalModel getModel() {
        return model;
    }

    public ZooPanel getZooPanel() {
        return zooPanel;
    }
}
