package de.hsb.simon.client.ui.tableModels;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import valueobjects.Person;

public class PersonenTableModel extends DefaultTableModel{
	
	private Vector<String> columnNames;
	private Vector<Vector<String>> data;
	
	public PersonenTableModel(List<Person> personen) {
		super();
		
		columnNames = new Vector<String>();
		columnNames.add("Personen Nummer");
		columnNames.add("Ganzer Name");
		columnNames.add("UserName");
		columnNames.add("E-Mail");
		columnNames.add("Addresse");
		columnNames.add("PLZ");
		columnNames.add("Wohnort");
		
		data = new Vector<Vector<String>>();
		updateDataVector(personen);
	}

	public void updateDataVector(List<Person> personen) {
		data.clear();
		
		for (Person p: personen) {
			Vector<String> personenVector = new Vector<String>();
			personenVector.add(p.getNummer()+"");
			personenVector.add(p.getName());
			personenVector.add(p.getUsername());
			personenVector.add(p.getEmail());
			personenVector.add(p.getStrasse());
			personenVector.add(p.getPlz());
			personenVector.add(p.getWohnort());
		
			data.add(personenVector);
		}
		setDataVector(data, columnNames);
	}
}
