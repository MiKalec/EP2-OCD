package br.com.ep.display;

import br.com.ep.components.cpu.Memory;
import br.com.ep.implementations.PalavraHorizontal;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoriaModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private Map<Long, PalavraHorizontal> mem;
    private List<Map.Entry<Long, PalavraHorizontal>> l;
    private String[] columnNames;

    public MemoriaModel(Memory m) {
        this.mem = m.getMap();
        this.columnNames = new String[]{"Posição", "Valor Inteiro"};
        this.l = new ArrayList(this.mem.entrySet());
    }

    public void update() {
        this.l.clear();
        this.l.addAll(this.mem.entrySet());
    }

    public int getRowCount() {
        return this.mem.size();
    }

    public String getColumnName(int col) {
        return this.columnNames[col];
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < this.mem.size() && rowIndex >= 0) {
            switch (columnIndex) {
                case 0:
                    return ((Map.Entry) this.l.get(rowIndex)).getKey();
                case 1:
                    return ((PalavraHorizontal) ((Map.Entry) this.l.get(rowIndex)).getValue()).getIntValue();
                default:
                    return null;
            }
        } else {
            return null;
        }
    }
}

