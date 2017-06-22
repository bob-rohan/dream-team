package rohan.dreamteam.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.GameweekStatistics;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.transformers.DreamTeamTransformer;

@Service
public class DreamTeamServiceImpl implements DreamTeamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DreamTeamServiceImpl.class);

	private static final String DREAM_TEAM_URL_INITIAL_DATA = "https://fantasy.premierleague.com/drf/bootstrap-static";

	private static final String DREAM_TEAM_URL_PLAYER_DATA = "https://fantasy.premierleague.com/drf/element-summary/";

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

		return generatedPlayers;

	}

	private Collection<Player> generatePlayers() {

		final String intialDataResponse = httpClientService.getDataString(DREAM_TEAM_URL_INITIAL_DATA);

		Collection<Player> players = dreamTeamTransformer.transformStringToPlayers(intialDataResponse);

		for (Player player : players) {
			LOGGER.info("ID: {}, Name: {}", player.getId(), player.getName());

			final String playerDataResponse = httpClientService
					.getDataString(DREAM_TEAM_URL_PLAYER_DATA + player.getId());

			Collection<GameweekStatistics> gameweeksStatistics = dreamTeamTransformer
					.transformStringToGameweekStatistics(playerDataResponse);

			player.setGameweeksStatistics(gameweeksStatistics);
		}

		return players;
	}
}
