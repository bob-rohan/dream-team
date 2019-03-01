package rohan.dreamteam.domain;

import org.springframework.data.annotation.Id;

public class Configuration {

	@Id
	private String id;
	
	private int nextGameweekId;

	public int getNextGameweekId() {
		return nextGameweekId;
	}

	public void setNextGameweekId(int nextGameweekId) {
		this.nextGameweekId = nextGameweekId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
