package it.polito.tdp.bar.model;

public class Event implements Comparable<Event> {
	
	public enum eventTypeEnum {
		ARRIVO_GRUPPO_CLIENTI,
		PARTENZA_GRUPPO_CLIENTI,
	}
	
	private eventTypeEnum eventType;
	private GruppoClienti gruppo;
	private long timeStamp;         
	
	public Event(long timeStamp, eventTypeEnum eventType, GruppoClienti gruppo) {
		this.eventType = eventType;
		this.gruppo = gruppo;
		this.timeStamp = timeStamp;
	}

	public eventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(eventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public GruppoClienti getGruppo() {
		return gruppo;
	}

	public void setGruppo(GruppoClienti gruppo) {
		this.gruppo = gruppo;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public int compareTo(Event o) {
		return Long.compare(this.timeStamp, o.timeStamp);
	}
				
}
