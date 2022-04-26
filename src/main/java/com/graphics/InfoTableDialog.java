package com.graphics;

import com.animals.Animal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Objects;

public class InfoTableDialog extends JDialog {
    private TableRowSorter<ZooModel> sorter;
    private static boolean isOpen;
    private JTable animalTable;
    private JTable totalRow;
    private ZooModel tableModel;
    private TotalModel totalModel;

    public static boolean getIsOpen(){
        return isOpen;
    }

    public void setIsOpen(boolean state){
        isOpen = state;
        setVisible(state);
    }


    public InfoTableDialog(AnimalModel model){
        tableModel = new ZooModel(model);
        totalModel = new TotalModel();

        animalTable = new JTable(tableModel);
        animalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        animalTable.setFillsViewportHeight(true);
        animalTable.setRowSorter(sorter = new TableRowSorter<>(tableModel));
        animalTable.setOpaque(false);
        animalTable.setPreferredScrollableViewportSize(animalTable.getPreferredSize());
        animalTable.getTableHeader().setReorderingAllowed(false);

        totalRow = new JTable(new TotalModel());
        totalRow.setTableHeader(null);
        totalRow.setFocusable(false);
        totalRow.setRowSelectionAllowed(false);
        totalRow.setOpaque(false);

        this.add(new JScrollPane(animalTable), BorderLayout.CENTER);
        this.add(totalRow, BorderLayout.SOUTH);

        this.setModalityType(ModalityType.DOCUMENT_MODAL);
        this.setLocationRelativeTo(this);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setIsOpen(false);
                dispose();
            }
        });

        this.pack();
    }

    public void updateTable(){
        tableModel.fireTableDataChanged();
        totalModel.fireTableDataChanged();
        animalTable.setPreferredScrollableViewportSize(animalTable.getPreferredSize());
        pack();
    }

    private class TotalModel extends AbstractTableModel {
        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0 -> {
                        return "Total";
                    }
                    case 6 -> {
                        return String.valueOf(ZooPanel.getTotalEatCount());
                    }
                }
                return null;
        }
    }

    private class ZooModel extends AbstractTableModel {
        private final ArrayList<Animal> animalArray;
        private final String[] columnNames = {"Animal", "Name", "Color", "Weight", "Hor.speed", "Var.speed", "Eat Counter"};

        public ZooModel(AnimalModel model) { this.animalArray = model.getAnimalModel(); };

        @Override
        public int getRowCount() { return animalArray.size(); }

        @Override
        public int getColumnCount() { return 7; }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class getColumnClass(int column) { return Objects.requireNonNull(getValueAt(0, column)).getClass(); }


        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < animalArray.size()){
                Animal animal = animalArray.get(rowIndex);
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
            return null;
        }
    }
}