package rohan.dreamteam.transformers;

import java.util.Collection;

import rohan.dreamteam.domain.Gameweek;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.fpldomain.initialData.InitialDataRoot;
import rohan.dreamteam.fpldomain.playerData.PlayerDataRoot;

public interface DreamTeamTransformer {

	Collection<Player> getPlayers(final InitialDataRoot initialDataRoot);

	Collection<Gameweek> getGameweeks(final PlayerDataRoot playerDataRoot);

}
