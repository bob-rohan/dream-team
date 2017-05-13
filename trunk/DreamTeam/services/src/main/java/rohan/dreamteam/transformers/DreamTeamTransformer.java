package rohan.dreamteam.transformers;

import java.util.Collection;

import rohan.dreamteam.domain.GameweekStatistics;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.fpldomain.initialdata.InitialDataRoot;
import rohan.dreamteam.fpldomain.playerdata.PlayerDataRoot;

public interface DreamTeamTransformer {

	Collection<Player> getPlayers(final InitialDataRoot initialDataRoot);

	Collection<GameweekStatistics> getGameweeks(final PlayerDataRoot playerDataRoot);

}
