package br.com.ep.display;

import br.com.ep.components.cpu.Firmware;
import br.com.ep.implementations.LinhaControle;

import javax.swing.table.AbstractTableModel;

public class LinhaControleModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private LinhaControle[] linhasControle;
    private String[] columnNames;

    public LinhaControleModel() {
        this.linhasControle = Firmware.instrucoes;
        this.columnNames = new String[]{"Portas", "Jump", "Próx.", "ULA", "RWAV", "Decode", "Linha"};
    }

    public int getRowCount() {
        return this.linhasControle.length;
    }

    public String getColumnName(int col) {
        return this.columnNames[col];
    }

    public int getColumnCount() {
        return this.columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > this.linhasControle.length || rowIndex < 0) {
            columnIndex = -1;
        }

        switch (columnIndex) {
            case 0:
                return this.stringfy(this.linhasControle[rowIndex].getPortas());
            case 1:
                return this.linhasControle[rowIndex].getJmpCond();
            case 2:
                return this.linhasControle[rowIndex].getProx();
            case 3:
                return this.linhasControle[rowIndex].getULA();
            case 4:
                return this.stringfy(this.linhasControle[rowIndex].getRWAV());
            case 5:
                return this.linhasControle[rowIndex].getDecode();
            case 6:
                return rowIndex;
            default:
                return null;
        }
    }

    private String stringfy(byte[] a) {
        StringBuilder resp = new StringBuilder();
        for (byte b : a) {
            resp.append(b);
        }

        return resp.toString();
    }
}
