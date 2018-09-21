package rohan.dreamteam.fpldomain.initialdata;

import java.util.Collection;

public class InitialDataRoot {

	private Collection<Element> elements;
	
	private Collection<Team> teams;
	
	private Collection<ElementType> element_types;

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
	
}
