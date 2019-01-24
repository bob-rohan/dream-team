package rohan.dreamteam.services;

import rohan.dreamteam.domain.Player;

public interface DreamTeamService {

	Iterable<Player> getPlayers();
	
	Iterable<Player> refreshPlayers();
	
	void setSelected(int fplId, boolean selected);

}
