package com.graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ZooTable extends JFrame {
    //private static ZooTable instance = null;
    private static int numOfFramesCurrentlyOpen = 0;
    private JTable table;
    private DefaultTableModel model;

    public ZooTable(ArrayList<ArrayList<String>> animalsInfo, int totalEatCounter){
        if (ZooTable.numOfFramesCurrentlyOpen != 0){
            Object[] o = {"OK"};
             JOptionPane.showOptionDialog(null, "Please close the previous window","ERROR", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.ERROR_MESSAGE, null, o, null);
        }
        else {
            final Object [] colNames = {"Animal", "Color", "Weight", "Hor.speed", "Var.speed", "Eat Counter"};
            Object[] lastRow = {"Total", "", "", "", "", String.valueOf(totalEatCounter)};
            model = new DefaultTableModel(colNames, 0);
            // The 0 argument is number rows.
            for (ArrayList<String> currAnimalInfo: animalsInfo){
                Object[] o = currAnimalInfo.toArray();
                model.addRow(o);
            }
            model.addRow(lastRow);
            table = new JTable(model);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("closing");
                    setVisible(false);
                    dispose();
                    ZooTable.numOfFramesCurrentlyOpen -= 1;
                }
            });
            this.add(scrollPane);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //this.dispose();
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            ZooTable.numOfFramesCurrentlyOpen += 1;
        }
    }
}

//    public static ZooTable getInstance(ArrayList<ArrayList<String>> animalsInfo, int totalEatCounter) {
//        if (instance == null) {
//            instance = new ZooTable(animalsInfo, totalEatCounter);
//        }
//        return instance;
//    }

