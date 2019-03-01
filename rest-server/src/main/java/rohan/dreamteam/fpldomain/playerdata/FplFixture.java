package rohan.dreamteam.fpldomain.playerdata;

import java.util.Scanner;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.sym.Name;

public class FplFixture {

	private int event_name;

	private int difficulty;

	private String opponent_short_name;

	private String opponent_name;

	private boolean is_home;

	public int getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {

		if (StringUtils.isEmpty(event_name)) {
			this.event_name = 0;
		} else {

			String[] tokens = event_name.split(" ");

			final String gameweekId = tokens[1];

			if (tokens.length != 2 || !gameweekId.matches("[0-9]+")) {
				throw new RuntimeException("Expected format \"Gameweek 8\"");
			}

			this.event_name = Integer.valueOf(gameweekId);
		}
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getOpponent_name() {
		return opponent_name;
	}

	public void setOpponent_name(String opponent_name) {
		this.opponent_name = opponent_name;
	}

	public boolean isIs_home() {
		return is_home;
	}

	public void setIs_home(boolean is_home) {
		this.is_home = is_home;
	}

	public String getOpponent_short_name() {
		return opponent_short_name;
	}

	public void setOpponent_short_name(String opponent_short_name) {
		this.opponent_short_name = opponent_short_name;
	}

}
