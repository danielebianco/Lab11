package it.polito.tdp.bar.model;

public class GruppoClienti {
	
	static private int idGruppoClienti = 0;
	private int id;
	private long timeArrival;
	private long durata;
	private float tolleranza;
	private int num_persone;
	private boolean soddisfatti;
	
	public GruppoClienti(long timeArrival, long durata, float tolleranza, int num_persone) {
		this.id = ++idGruppoClienti;
		this.timeArrival = timeArrival;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.num_persone = num_persone;
		this.soddisfatti = false;
	}

	public static int getIdGruppoClienti() {
		return idGruppoClienti;
	}

	public static void setIdGruppoClienti(int idGruppoClienti) {
		GruppoClienti.idGruppoClienti = idGruppoClienti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimeArrival() {
		return timeArrival;
	}

	public void setTimeArrival(long timeArrival) {
		this.timeArrival = timeArrival;
	}

	public long getDurata() {
		return durata;
	}

	public void setDurata(long durata) {
		this.durata = durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	public int getNum_persone() {
		return num_persone;
	}

	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}

	public boolean isSoddisfatti() {
		return soddisfatti;
	}

	public void setSoddisfatti(boolean soddisfatti) {
		this.soddisfatti = soddisfatti;
	}
	
	@Override
	public String toString() {
		return "Customer#" + id;
	}
	
}
