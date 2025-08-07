/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.gui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class MeuListModel<T> implements ListModel<T> {
    
    private final List<T> data;
    private final List<ListDataListener> listeners;

    public MeuListModel(List<T> values) {
        data = new ArrayList<>(values);
        listeners = new ArrayList<>();
    }
    
    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public T getElementAt(int index) {
        return data.get(index);
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
