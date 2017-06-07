package rohan.dreamteam.transformers;

import java.util.Collection;

import rohan.dreamteam.domain.GameweekStatistics;
import rohan.dreamteam.domain.Player;

public interface DreamTeamTransformer {

	Collection<Player> transformStringToPlayers(final String playersData);

	Collection<GameweekStatistics> transformStringToGameweekStatistics(final String playerData);

}
