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

	private static final Logger LOGGER = LoggerFactory.getLogger(DreamTeamServiceImpl.class);

	private static final String DREAM_TEAM_URL_INITIAL_DATA = "https://fantasy.premierleague.com/drf/bootstrap-static";

	private static final String DREAM_TEAM_URL_PLAYER_DATA = "https://fantasy.premierleague.com/drf/element-summary/";

	@Autowired
	private HttpClientService httpClientService;

	@Autowired
	private DataConverter dataConverter;

	@Autowired
	private DreamTeamTransformer dreamTeamTransformer;

	private Collection<Player> players;

	@Override
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

		return dataConverter.convertJsonToInitialDataRoot(responseBody);
	}

	private PlayerDataRoot getPlayerData(int playerId) {
		final String responseBody = httpClientService.getDataString(DREAM_TEAM_URL_PLAYER_DATA + playerId);

		return dataConverter.convertJsonToPlayerDataRoot(responseBody);
	}
}
