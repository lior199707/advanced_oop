package com.graphics;

import com.animals.Animal;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Objects;

public class InfoTableMVC extends JFrame {
    private TableRowSorter<AnimalModel> sorter;
    private JTextField tbFilterText;

    public InfoTableMVC(ArrayList<Animal> animal){
        super("Info");

        AnimalModel model = new AnimalModel(animal);
        JTable table = new JTable(model);
        table.setRowSorter(sorter = new TableRowSorter<AnimalModel>(model));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);

        this.add(new JScrollPane(table));
        this.add(tbFilterText = new JTextField());
        tbFilterText.setToolTipText("Filter Name Column");
        tbFilterText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { newFilter(); }
            public void removeUpdate(DocumentEvent e) { newFilter(); }
            public void changedUpdate(DocumentEvent e) { newFilter(); }
        });
        this.pack();
        this.setVisible(true);
    }

    private void newFilter() {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), 1));
        } catch (java.util.regex.PatternSyntaxException e) {
            // If current expression doesn't parse, don't update.
        }
    }

    private class AnimalModel extends AbstractTableModel {
        private ArrayList<Animal> data;
        private final String[] columnNames = {"Animal", "Color", "Weight", "Hor.speed", "Var.speed", "Eat Counter"};

        public AnimalModel(ArrayList<Animal> data) { this.data = data; }
        @Override
        public int getRowCount() { return data.size(); }
        @Override
        public int getColumnCount() { return 5; }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class getColumnClass(int column) { return Objects.requireNonNull(getValueAt(0, column)).getClass(); }



        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Animal animal = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return animal.getName();
                case 1:
                    return animal.getWeight();
                case 2:
                    return animal.getDiet();
                case 3:
                    return animal.getTotalDistance();
            }
            return null;
        }
    }
}