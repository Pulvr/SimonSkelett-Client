package de.hsb.simon.client.ui.tableModels;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import valueobjects.MassengutWare;
import valueobjects.Ware;

public class WarenkorbTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    
    private Vector<String> columnNames;
	private Vector<Vector<String>> data;
	// Das DecimalFormat ist ein Member der WarenTableModel Klasse um in updateDataVector wiederverwendet zu werden.
	private DecimalFormat df = new DecimalFormat("#,##0.00");

	
	public WarenkorbTableModel(Vector<Ware> waren) {
		super();
		
		columnNames = new Vector<String>();
		columnNames.add("Waren Nummer");
		columnNames.add("Titel");
		columnNames.add("Bestand");
        columnNames.add("Preis");
        columnNames.add("Pack.Gr.");
        columnNames.add("Pack.Preis");
		
		data = new Vector<Vector<String>>();
		updateDataVector(waren);
	}

	public void updateDataVector(List<Ware> waren) {
		data.clear();
		
		for (Ware w: waren) {
			Vector<String> warenVector = new Vector<String>();
			// String.valueOf() um Laufzeitoptimierungen der Java VM auszunutzen
			warenVector.add(String.valueOf(w.getNummer()));
			warenVector.add(String.valueOf(w.getBezeichnung()));
			warenVector.add(String.valueOf(w.getBestand()));
			warenVector.add(df.format(w.getPreis()) + " �");
			// die Warenlist beinhaltet auch Massengutwaren und muss daher auf diesen Typ gepr�ft werden
			if (w instanceof MassengutWare) {
			    // (MassengutWare) w    ist ein sog. Type-Cast um Java zu zwingen die Variable "w" als MassengutWare zu behandeln.
			    MassengutWare mw = (MassengutWare) w;
                warenVector.add(String.valueOf(mw.getPackungsGroesse()));
                warenVector.add(String.valueOf(df.format(mw.getRechnungsPreis()) + " �"));
			} else {
                warenVector.add("");
                warenVector.add("");
			}
			data.add(warenVector);
		}
		setDataVector(data, columnNames);
	}
	
	
	@Override
	/**
	 * Klasse von javax.swing.JTable �berschrieben um das editieren aller Zellen zu verbieten
	 */
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}

