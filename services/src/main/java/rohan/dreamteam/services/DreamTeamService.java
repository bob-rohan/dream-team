package rohan.dreamteam.services;

import java.util.Collection;

import rohan.dreamteam.domain.Player;

public interface DreamTeamService {

	Collection<Player> getPlayers();
	
	Collection<Player> refreshPlayers();

}
