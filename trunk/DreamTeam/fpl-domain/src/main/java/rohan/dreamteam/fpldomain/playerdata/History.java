package rohan.dreamteam.fpldomain.playerdata;

import java.util.Date;

public class History {

	/**
	 * Consider Java 8 Date upgrade
	 */
	private Date kickoff_time;

	private int yellow_cards;

	private int minutes;

	private int round;

	private int total_points;

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getTotal_points() {
		return total_points;
	}

	public void setTotal_points(int total_points) {
		this.total_points = total_points;
	}

	public Date getKickoff_time() {
		return kickoff_time;
	}

	public void setKickoff_time(Date kickoff_time) {
		this.kickoff_time = kickoff_time;
	}

	public int getYellow_cards() {
		return yellow_cards;
	}

	public void setYellow_cards(int yellow_cards) {
		this.yellow_cards = yellow_cards;
	}

}
