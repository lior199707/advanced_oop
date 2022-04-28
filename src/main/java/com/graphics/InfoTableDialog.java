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

/**
 * class InfoTable extends JDialog, presents a dynamic changing table presenting the zoo animals information
 * in a JDialog.
 */
public class InfoTableDialog extends JDialog {
    /**
     * sorter of the table.
     */
    private TableRowSorter<ZooModel> sorter;
    /**
     * Boolean value, indicates if the table dialog is open or not.
     */
    private static boolean isOpen;
    /**
     * the table presenting information about the animals.
     */
    private JTable animalTable;
    /**
     * one row sub-table holding the value of the total eating amount the zoo animals made.
     */
    private JTable totalRow;
    /**
     * the zoo table model.
     */
    private ZooModel tableModel;
    /**
     * total table (sub-table) model.
     */
    private TotalModel totalModel;

    //Ctor

    /**
     * InfoTableDialog constructor, opens a dialog window containing a table which presents
     * all the zoo animals information. and a sub table of one row holding the total eat counter of the zoo panel.
     *
     * @param model - the model of the main table.
     */
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
            // called when closing the dialog holding the table.
            public void windowClosing(WindowEvent e) {
                //indicating the table is mot open.
                setIsOpen(false);
                dispose();
            }
        });

        this.pack();
    }

    //end Ctor

    /**
     * static method. is open getter.
     * @return Boolean value indocates if the table dialog is open or not.
     */
    public static boolean getIsOpen(){
        return isOpen;
    }

    /**
     * is open setter
     * @param state - Boolean value to set is open attribute to.
     */
    public void setIsOpen(boolean state){
        isOpen = state;
        setVisible(state);
    }


    /**
     * updates the table after changes in the animals model.
     * i.e. animal was eaten thus removed from the model, animal ate and her eat counter needs to be updated.
     */
    public void updateTable(){
        tableModel.fireTableDataChanged();
        totalModel.fireTableDataChanged();
        animalTable.setPreferredScrollableViewportSize(animalTable.getPreferredSize());
        pack();
    }

    /**
     * private class TotalModel, presents a table of one row
     * containing the total amount of eating the zoo animals made.
     */
    private class TotalModel extends AbstractTableModel {
        /**
         * @return constant integer value, the number of rows the table has(1).
         */
        @Override
        public int getRowCount() {
            return 1;
        }

        /**
         *
         * @return constant integer value, the number of columns the table has(7).
         */
        @Override
        public int getColumnCount() {
            return 7;
        }

        /**
         *
         * @param rowIndex - the current row.
         * @param columnIndex - the current column.
         * @return String, the information to be presented based on the current row and column, if the column number
         * doesn't exist returns null.
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
               //ignores indices 1 to 5 because it contains an empty string
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


    /**
     * private class ZooModel extends AbstractTableModel, handles the model of the zoo table.
     */
    private class ZooModel extends AbstractTableModel {
        /**
         * a list of the zoo animals
         */
        private final ArrayList<Animal> animalArray;
        /**
         * The column names of the table held by the header.
         */
        private final String[] columnNames = {"Animal", "Name", "Color", "Weight", "Hor.speed", "Var.speed", "Eat Counter"};

        /**
         * ZooModel constructor, sets the array containing the zoo animals to hold the current animals in the model.
         * @param model - the model containing the zoo animals.
         */
        public ZooModel(AnimalModel model) { this.animalArray = model.getAnimalModel(); };

        /**
         * @return integer value, table number of rows(based on the current number of animals in the model).
         */
        @Override
        public int getRowCount() { return animalArray.size(); }

        /**
         * @return constant integer value, table number of columns(7).
         */
        @Override
        public int getColumnCount() { return 7; }

        /**
         * loads the header of a specific column in the table.
         * @param column integer, the number of the column to load its header.
         * @return String value, the header name of the corresponding column.
         */
        @Override
        public String getColumnName(int column) {
            //
            return columnNames[column];
        }

        /**
         * returns the class of the content stored in a specific column of the table.
         * @param column - the column to get its stored content class.
         * @return the class of the content stored in the column.
         */
        @Override
        public Class getColumnClass(int column) { return Objects.requireNonNull(getValueAt(0, column)).getClass(); }


        /**
         *
         * @param rowIndex - the current row.
         * @param columnIndex - the current column.
         * @return String, the information to be presented based on the current row and column, if the column number
         * doesn't exist returns null.
         */
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