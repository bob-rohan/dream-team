package rohan.dreamteam.domain;

import java.util.Date;

public class Gameweek {

	private Date kickoff;

	private int totalPoints;

	private int minutes;

	private int gameweek;

	private int yellowCards;

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Date getKickoff() {
		return kickoff;
	}

	public void setKickoff(Date kickoff) {
		this.kickoff = kickoff;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getGameweek() {
		return gameweek;
	}

	public void setGameweek(int gameweek) {
		this.gameweek = gameweek;
	}

	public int getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}
}
