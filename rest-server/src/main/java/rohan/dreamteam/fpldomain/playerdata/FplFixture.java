package rohan.dreamteam.fpldomain.playerdata;

import org.springframework.util.StringUtils;

public class FplFixture {

	private int event_name;

	private int difficulty;

	private int team_h;
	
	private int team_a;

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


	public boolean isIs_home() {
		return is_home;
	}

	public void setIs_home(boolean is_home) {
		this.is_home = is_home;
	}

	public int getTeam_h() {
		return team_h;
	}

	public void setTeam_h(int team_h) {
		this.team_h = team_h;
	}

	public int getTeam_a() {
		return team_a;
	}

	public void setTeam_a(int team_a) {
		this.team_a = team_a;
	}

}
