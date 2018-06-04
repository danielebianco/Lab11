package it.polito.tdp.bar.model;

import java.util.*;

public class Model {
	private Random rd;
	private Statistiche statistiche;
	private Queue<Event> eventList;
	private Map<Integer, Tavolo> tables;

	public Model() {
		rd = new Random(42);
		statistiche = new Statistiche();
		eventList = new PriorityQueue<Event>();
		tables = new HashMap<Integer, Tavolo>();
	}
	
	public void addEvent(Event e) {
		eventList.add(e);
	}

	public void simulate() {
		while (!eventList.isEmpty()) {
			doSimulationStep();
		}
	}

	private long doSimulationStep() {
		if(eventList.isEmpty()) {
			return Long.MAX_VALUE;
		}
		Event e = eventList.remove();

		switch (e.getEventType()) {
		
		case ARRIVO_GRUPPO_CLIENTI:
			
			Tavolo table = findAvailableTable(e.getGruppo().getNum_persone());
			
			if(table != null) {
				
				// Assegno il tavolo ai clienti
				table.setIdCustomers(e.getGruppo().getId());
				table.setLibero(false);
				e.getGruppo().setSoddisfatti(true);
				
				// Creo un nuovo evento per simulare i clienti che escono dal locale
				Event eventCustomersLeave = new Event(e.getTimeStamp() + e.getGruppo().getDurata(),
						Event.eventTypeEnum.PARTENZA_GRUPPO_CLIENTI, e.getGruppo());
				
				addEvent(eventCustomersLeave);
			
			} else {
				
				float tolleranza = e.getGruppo().getTolleranza();
				float probabilita = rd.nextFloat();
				
				if(probabilita <= tolleranza) {
					e.getGruppo().setSoddisfatti(true);
				}
				else {
					e.getGruppo().setSoddisfatti(false);
				}
			}
			statistiche.aggiungiClienti(e.getGruppo());
			
			break;
			
		case PARTENZA_GRUPPO_CLIENTI:
			
			Tavolo freeTable = this.trovaTavolo(e.getGruppo().getId());
			freeTable.setLibero(true);
			freeTable.setIdCustomers(-1);
			
			break;
			
		default:
			throw new IllegalArgumentException();
		}
				
		return e.getTimeStamp();
	}


	private Tavolo findAvailableTable(int numPersone) {

		int postiTavoloMin = Integer.MAX_VALUE;
		Tavolo returnTable = null;

		// Itero su tutti i tavoli
		for (Tavolo table : new LinkedList<Tavolo>(tables.values())) {

			// Controllo i requisiti minimi
			if (table.isLibero() && numPersone >= 0.5 * table.getNumPostiASedere()) {

				// Cerco il tavolo con il minimo numero di posti a sedere.
				if (postiTavoloMin > table.getNumPostiASedere()) {
					returnTable = table;
					postiTavoloMin = table.getNumPostiASedere();
				}
			}
		}

		return returnTable;
	}

	public Tavolo trovaTavolo(int idCustomers) {

		for (Tavolo table : tables.values()) {
			if (table.getIdCustomers() == idCustomers)
				return table;
		}
		return null;
	}

	public void addTable(int numPosti) {
		Tavolo temp = new Tavolo(numPosti);
		tables.put(temp.getIdTable(), temp);
		statistiche.setNumTavoli(tables.size());
	}

	public Statistiche getStats() {
		return this.statistiche;
	}

	public void cleanup() {
		eventList.clear();
		statistiche.cleanup();
	}
}