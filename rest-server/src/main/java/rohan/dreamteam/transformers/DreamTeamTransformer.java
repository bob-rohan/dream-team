package rohan.dreamteam.transformers;

import java.util.Collection;

import rohan.dreamteam.domain.Configuration;
import rohan.dreamteam.domain.Gameweek;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.fpldomain.initialdata.InitialDataRoot;

public interface DreamTeamTransformer {

	// TODO: pass back domain not fpl domain object
	InitialDataRoot transformInitialData(final String initialData);
	
	Collection<Player> getPlayers(final InitialDataRoot initialDataRoot);
	
    Configuration getConfiguration(InitialDataRoot initialDataRoot);
	
    Configuration getConfiguration(InitialDataRoot initialDataRoot, Configuration configuration);
	
    Collection<Gameweek> getGameweeks(InitialDataRoot initialDataRoot);
    
	Player transformStringToPlayer(final String playerData, final Player player);
	
	Collection<Player> transformPriceChangeStringToPlayers(final String priceChangeHtml);

}
