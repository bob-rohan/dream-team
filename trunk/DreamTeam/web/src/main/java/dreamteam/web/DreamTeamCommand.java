package dreamteam.web;

import java.util.Collection;

import dreamteam.domain.Defender;
import dreamteam.domain.Forward;
import dreamteam.domain.Goalkeeper;
import dreamteam.domain.Midfielder;
import dreamteam.domain.Player;

public class DreamTeamCommand {

	private Collection<Player> players;
	
	private Collection<Goalkeeper> goalkeepers;
	
	private Collection<Defender> defenders;
	
	private Collection<Midfielder> midfielders;
	
	private Collection<Forward> forwards;
	
	private Collection<Player> dreamTeam;

	public Collection<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}

	public Collection<Goalkeeper> getGoalkeepers() {
		return goalkeepers;
	}

	public void setGoalkeepers(Collection<Goalkeeper> goalkeepers) {
		this.goalkeepers = goalkeepers;
	}

	public Collection<Defender> getDefenders() {
		return defenders;
	}

	public void setDefenders(Collection<Defender> defenders) {
		this.defenders = defenders;
	}

	public Collection<Midfielder> getMidfielders() {
		return midfielders;
	}

	public void setMidfielders(Collection<Midfielder> midfielders) {
		this.midfielders = midfielders;
	}

	public Collection<Forward> getForwards() {
		return forwards;
	}

	public void setForwards(Collection<Forward> forwards) {
		this.forwards = forwards;
	}

	public Collection<Player> getDreamTeam() {
		return dreamTeam;
	}

	public void setDreamTeam(Collection<Player> dreamTeam) {
		this.dreamTeam = dreamTeam;
	}

	
}
