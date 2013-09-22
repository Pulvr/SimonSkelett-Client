package de.hsb.simon.client.ui.gui;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import valueobjects.Ware;

public class WarenTableModel extends DefaultTableModel {

	private Vector<String> columnNames;
	private Vector<Vector<String>> data;
	
	public WarenTableModel(List<Ware> waren) {
		super();
		
		columnNames = new Vector<String>();
		columnNames.add("Waren Nummer");
		columnNames.add("Titel");
		columnNames.add("Bestand");
		columnNames.add("Preis");
		
		data = new Vector<Vector<String>>();
		updateDataVector(waren);
	}

	public void updateDataVector(List<Ware> waren) {
		data.clear();
		
		for (Ware w: waren) {
			Vector<String> warenVector = new Vector<String>();
			warenVector.add(w.getNummer()+"");
			warenVector.add(w.getBezeichnung());
			warenVector.add(w.getBestand()+"");
			DecimalFormat df = new DecimalFormat("#,##0.00");
			warenVector.add(df.format((w.getPreis()))+" �");
			data.add(warenVector);
		}
		setDataVector(data, columnNames);
	}
	
	@Override
	/**
	 * Klasse von javax.swing.JTable überschrieben um das editieren aller Zellen zu verbieten
	 */
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}
