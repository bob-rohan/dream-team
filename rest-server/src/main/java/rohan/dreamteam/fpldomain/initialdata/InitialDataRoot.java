package rohan.dreamteam.fpldomain.initialdata;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitialDataRoot {

	private Collection<Element> elements;
	
	private Collection<Team> teams;
	
	private Collection<ElementType> element_types;
	
	private Collection<Event> events;
	
	@JsonProperty("next-event")
	private int nextEvent;

	public Collection<Element> getElements() {
		return elements;
	}

	public void setElements(Collection<Element> elements) {
		this.elements = elements;
	}

	public Collection<Team> getTeams() {
		return teams;
	}

	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

	public Collection<ElementType> getElement_types() {
		return element_types;
	}

	public void setElement_types(Collection<ElementType> element_types) {
		this.element_types = element_types;
	}

	public Collection<Event> getEvents() {
		return events;
	}

	public void setEvents(Collection<Event> events) {
		this.events = events;
	}

	public int getNextEvent() {
		return nextEvent;
	}

	public void setNextEvent(int nextEvent) {
		this.nextEvent = nextEvent;
	}
	
}
