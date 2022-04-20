package com.graphics;

import com.animals.Animal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class InfoTableDialog extends JDialog {
    private TableRowSorter<ZooModel> sorter;
    private static boolean isOpen;
    private JTable table;
    private ZooModel tableModel;

    public static boolean getIsOpen(){
        return isOpen;
    }

    public void setIsOpen(boolean state){
        isOpen = state;
        setVisible(state);
    }


    public InfoTableDialog(AnimalModel model){
        tableModel = new ZooModel(model);

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.setRowSorter(sorter = new TableRowSorter<>(tableModel));
        table.setOpaque(false);
        table.getTableHeader().setFont(new Font("Default", Font.BOLD,12));
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        this.setSize(600,250);
        this.add(new JScrollPane(table));

        this.setModalityType(ModalityType.DOCUMENT_MODAL);
        this.setLocationRelativeTo(this);
//        this.pack();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setIsOpen(false);
                dispose();
            }
        });
    }

    public void updateTable(){
        tableModel.fireTableDataChanged();
    }


    public class ZooModel extends AbstractTableModel {
        private ArrayList<Animal> data;
        private final String[] columnNames = {"Animal", "Name", "Color", "Weight", "Hor.speed", "Var.speed", "Eat Counter"};

        public ZooModel(AnimalModel model) { this.data = model.getModel(); }


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
            if (rowIndex < data.size()){
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
            }
            else {
                switch (columnIndex) {
                    case 0 -> {
                        return "Total";
                    }
                    case 6 -> {
                        return String.valueOf(ZooPanel.getTotalEatCount());
                    }
                }
            }

            return null;
        }
    }
}