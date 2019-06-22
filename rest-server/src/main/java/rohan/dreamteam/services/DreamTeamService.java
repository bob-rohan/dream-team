package rohan.dreamteam.services;

import rohan.dreamteam.domain.Configuration;
import rohan.dreamteam.domain.Gameweek;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.domain.Team;

public interface DreamTeamService {

	Iterable<Player> getPlayers();
	
	Iterable<Player> refreshPlayers();
	
	void setSelected(int fplId, boolean selected);

	Configuration getConfiguration();

	Iterable<Gameweek> getGameweeks();

	Iterable<Team> getTeams();

}
