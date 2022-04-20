package com.graphics;

import com.animals.Animal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import javax.swing.table.*;

public class InfoTableMVC extends JDialog {
    private TableRowSorter<ZooModel> sorter;
    private static boolean isOpen = false;
    JTable table;

    public static boolean getIsOpen(){
        return isOpen;
    }
    public InfoTableMVC(AnimalModel model){
        ZooModel tableModel = new ZooModel(model);

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.setRowSorter(sorter = new TableRowSorter<>(tableModel));
        table.setOpaque(false);
        table.getTableHeader().setFont(new Font("Default", Font.BOLD,12));
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        this.add(new JScrollPane(table), BorderLayout.NORTH);
//        this.add(new JScrollPane(kaka), BorderLayout.SOUTH);

        this.setModalityType(ModalityType.DOCUMENT_MODAL);
        this.setSize(500,500);
        this.setLocationRelativeTo(this);
        this.pack();
        isOpen = true;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                setVisible(false);
                isOpen = false;
            }
        });
    }


    public class ZooModel extends AbstractTableModel {
        private ArrayList<Animal> data;
        private final String[] columnNames = {"Animal", "Name", "Color", "Weight", "Hor.speed", "Var.speed", "Eat Counter"};

        public ZooModel(AnimalModel model) { this.data = model.getModel();}

        @Override
        public int getRowCount() { return data.size() + 1 ; }

        @Override
        public int getColumnCount() { return 7; }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class getColumnClass(int column){return String.class;}
//        public Class getColumnClass(int column) { return Objects.requireNonNull(getValueAt(0, column)).getClass(); }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Animal animal = data.get(rowIndex);
            switch (columnIndex) {
                case 0 -> {
                    return animal.getAnimalName();
                }
                case 1 -> {
                    return animal.getName();
                }
                case 2 -> {
                    return animal.getColor();
                }
                case 3 -> {
                    return animal.getWeight();
                }
                case 4 -> {
                    return animal.getHorSpeed();
                }
                case 5 -> {
                    return animal.getVerSpeed();
                }
                case 6 -> {
                    return animal.getEatCount();
                }
            }
            return null;
        }
    }
}