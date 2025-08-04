package com.alex.dcc025.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class MeuComboModel<T> implements ComboBoxModel<T> {

    private final List<T> items = new ArrayList<>();
    private final List<ListDataListener> listeners = new ArrayList<>();

    private T selected = null;

    public MeuComboModel(List<T> items, boolean nullable) {

        if (nullable) this.items.add(null);
       
        this.items.addAll(items);
        
        if (this.items.size() != 0) this.selected = this.items.get(0);
        
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (T) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public T getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }
    
}
