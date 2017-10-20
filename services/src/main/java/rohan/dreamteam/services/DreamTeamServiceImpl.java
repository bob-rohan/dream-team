package rohan.dreamteam.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.Player;
import rohan.dreamteam.transformers.DreamTeamTransformer;

@Service
@Profile("!mock")
public class DreamTeamServiceImpl implements DreamTeamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DreamTeamServiceImpl.class);

	private static final String DREAM_TEAM_URL_INITIAL_DATA = "https://fantasy.premierleague.com/drf/bootstrap-static";

	private static final String DREAM_TEAM_URL_PLAYER_DATA = "https://fantasy.premierleague.com/drf/element-summary/";
	
	private static final String FPL_STATISTICS = "http://www.fplstatistics.co.uk/Home/IndexWG";

	@Autowired
	private HttpClientService httpClientService;

	@Autowired
	private DreamTeamTransformer dreamTeamTransformer;

	private Collection<Player> generatedPlayers;

	@Override
	public Collection<Player> getPlayers() {

		if (generatedPlayers == null) {
			generatedPlayers = generatePlayers();
		}

		return generatedPlayers = generatePlayers();

	}

	private Collection<Player> generatePlayers() {

		final String priceChangeHtml = httpClientService.getDataString(FPL_STATISTICS);
		
		final Collection<Player> priceChangePlayers = dreamTeamTransformer.transformPriceChangeStringToPlayers(priceChangeHtml);
		
		final String intialDataResponse = httpClientService.getDataString(DREAM_TEAM_URL_INITIAL_DATA);

		Collection<Player> players = dreamTeamTransformer.transformStringToPlayers(intialDataResponse);
		
		for (Player player : players) {
			LOGGER.info("ID: {}, Name: {}", player.getId(), player.getName());

			final String playerDataResponse = httpClientService
					.getDataString(DREAM_TEAM_URL_PLAYER_DATA + player.getId());
			
			dreamTeamTransformer.transformStringToPlayer(playerDataResponse, player);
			
			applyPriceChangeStats(priceChangePlayers, player);
			
			LOGGER.trace("Player = {}", player);
		}

		return players;
	}
	
	private void applyPriceChangeStats(Collection<Player> priceChangePlayers, final Player player){
		
		for(Player priceChangePlayer : priceChangePlayers){
			if(priceChangePlayer.getName().equalsIgnoreCase(player.getName()) && priceChangePlayer.getTeam().getName().equalsIgnoreCase(player.getTeam().getName())){
				player.setLikelihoodOfPriceChange(priceChangePlayer.getLikelihoodOfPriceChange());
			}
		}
		
	}
}
