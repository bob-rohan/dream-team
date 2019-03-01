package rohan.dreamteam.domain;

import org.springframework.data.annotation.Id;

public class Gameweek {
	
	@Id
	private String id;
	
	private int gameweek;
	
	public int getGameweek() {
		return gameweek;
	}

	public void setGameweek(int gameweek) {
		this.gameweek = gameweek;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
