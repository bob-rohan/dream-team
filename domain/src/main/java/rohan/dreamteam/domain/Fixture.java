package rohan.dreamteam.domain;

public class Fixture {

	private int gameweek;
	
	private int difficulty;
	
	private String oponentName;
	
	private String oponentShortName;
	
	private boolean isHome;

	public int getGameweek() {
		return gameweek;
	}

	public void setGameweek(int gameweek) {
		this.gameweek = gameweek;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getOponentName() {
		return oponentName;
	}

	public void setOponentName(String oponentName) {
		this.oponentName = oponentName;
	}

	public String getOponentShortName() {
		return oponentShortName;
	}

	public void setOponentShortName(String oponentShortName) {
		this.oponentShortName = oponentShortName;
	}

	public boolean isHome() {
		return isHome;
	}

	public void setHome(boolean isHome) {
		this.isHome = isHome;
	}
	
}
