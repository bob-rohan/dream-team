package rohan.dreamteam.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.GameweekStatistics;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.fpldomain.initialdata.InitialDataRoot;
import rohan.dreamteam.fpldomain.playerdata.PlayerDataRoot;
import rohan.dreamteam.transformers.DreamTeamTransformer;

@Service
public class DreamTeamServiceImpl implements DreamTeamService {

	private final static Logger LOGGER = LoggerFactory.getLogger(DreamTeamServiceImpl.class);

	private final static String DREAM_TEAM_URL_INITIAL_DATA = "https://fantasy.premierleague.com/drf/bootstrap-static";

	private final static String DREAM_TEAM_URL_PLAYER_DATA = "https://fantasy.premierleague.com/drf/element-summary/";

	@Autowired
	private HttpClientService httpClientService;

	@Autowired
	private DataConverter dataConverter;

	@Autowired
	private DreamTeamTransformer dreamTeamTransformer;

	private Collection<Player> players;

	public Collection<Player> getPlayers() {

		if (players == null) {
			generatePlayers();
		}

		return players;

	}

	private void generatePlayers() {

		final InitialDataRoot initialDataRoot = getInitialData();

		players = dreamTeamTransformer.getPlayers(initialDataRoot);

		for (Player player : players) {
			LOGGER.info("ID: {}, Name: {}", player.getId(), player.getName());

			final PlayerDataRoot playerDataRoot = getPlayerData(player.getId());

			Collection<GameweekStatistics> gameweeksStatistics = dreamTeamTransformer.getGameweeks(playerDataRoot);

			player.setGameweeksStatistics(gameweeksStatistics);
		}
	}

	private InitialDataRoot getInitialData() {
		final String responseBody = httpClientService.getDataString(DREAM_TEAM_URL_INITIAL_DATA);

		final InitialDataRoot initialDataRoot = dataConverter.convertJsonToInitialDataRoot(responseBody);

		return initialDataRoot;
	}

	private PlayerDataRoot getPlayerData(int playerId) {
		final String responseBody = httpClientService.getDataString(DREAM_TEAM_URL_PLAYER_DATA + playerId);

		final PlayerDataRoot playerDataRoot = dataConverter.convertJsonToPlayerDataRoot(responseBody);

		return playerDataRoot;
	}
}
