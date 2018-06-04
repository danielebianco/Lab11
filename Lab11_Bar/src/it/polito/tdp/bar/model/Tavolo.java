package it.polito.tdp.bar.model;

public class Tavolo {
	
	static private int totaleTavoli = 0;
	private int idCustomers;
	private int idTable;
	private int numPostiASedere;
	private boolean libero;
	
	public Tavolo(int numPostiASedere) {
		this.idCustomers = -1;
		this.idTable = ++totaleTavoli;
		this.numPostiASedere = numPostiASedere;
		this.libero = true;
	}

	public static int getTotaleTavoli() {
		return totaleTavoli;
	}

	public static void setTotaleTavoli(int totaleTavoli) {
		Tavolo.totaleTavoli = totaleTavoli;
	}

	public int getIdCustomers() {
		return idCustomers;
	}

	public void setIdCustomers(int idCustomers) {
		this.idCustomers = idCustomers;
	}

	public int getIdTable() {
		return idTable;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public int getNumPostiASedere() {
		return numPostiASedere;
	}

	public void setNumPostiASedere(int numPostiASedere) {
		this.numPostiASedere = numPostiASedere;
	}

	public boolean isLibero() {
		return libero;
	}

	public void setLibero(boolean libero) {
		this.libero = libero;
	}
	
}
