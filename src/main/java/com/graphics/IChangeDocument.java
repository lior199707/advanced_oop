package com.graphics;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public interface IChangeDocument extends DocumentListener {
    void changeDocument(DocumentEvent e);
    @Override
    default void insertUpdate(DocumentEvent e){
        changeDocument(e);
    }
    @Override
    default void removeUpdate(DocumentEvent e){
        changeDocument(e);
    }
    @Override
    default void changedUpdate(DocumentEvent e){
        changeDocument(e);
    }
}
