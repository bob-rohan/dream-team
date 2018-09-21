package rohan.dreamteam.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.Fixture;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.transformers.DreamTeamTransformer;

/**
 * TODO: untested following fixture difficulty changes
 * 
 * @author Admin
 *
 */
@Service
@Profile("mock")
public class DreamTeamServiceMock implements DreamTeamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DreamTeamServiceMock.class);

	private static final String STATIC_DATA_PATH = "/fpl1617/initialData/initialData.json";

	private static final String PLAYER_DATA_PATH = "/fpl1617/playerData/";

	@Autowired
	private DreamTeamTransformer transformer;

	private Collection<Player> players;

	@Override
	public Collection<Player> getPlayers() {

		try {
			List<String> lines = Files.readAllLines(Paths.get(STATIC_DATA_PATH));
			String playersData = lines.toString();
			// get rid of the square brackets from the array
			playersData = playersData.substring(1, playersData.length() - 1);

			players = transformer.transformStringToPlayers(playersData);

			for (Player player : players) {
				final List<String> playerDataResponseLines = Files
						.readAllLines(Paths.get(PLAYER_DATA_PATH + "player" + player.getFplId() + ".json"));

				String playerData = playerDataResponseLines.toString();
				// get rid of the square brackets from the array
				playerData = playerData.substring(1, playerData.length() - 1);

				transformer.transformStringToPlayer(playerData, player);
				
				Collection<Fixture> fixtures = new ArrayList<>();

				Fixture fixture = new Fixture();

				fixture.setGameweek(8);
				fixture.setDifficulty(1);
				fixture.setOponentShortName("SWA");
				fixture.setHome(true);

				Fixture fixture2 = new Fixture();
				fixture2.setGameweek(9);
				fixture2.setDifficulty(5);
				fixture2.setOponentShortName("MCI");
				fixture2.setHome(false);
				
				Fixture fixture3 = new Fixture();
				fixture3.setGameweek(9);
				fixture3.setDifficulty(3);
				fixture3.setOponentShortName("CHE");
				fixture3.setHome(false);

				fixtures.add(fixture);
				fixtures.add(fixture2);
				fixtures.add(fixture3);

				player.setFixtures(fixtures);
				
				player.setLikelihoodOfPriceChange(95);
			}

		} catch (IOException e) {
			LOGGER.error("exception whilst creating mock data", e);
		}
		return players;
	}
	
	@Override
	public Collection<Player> refreshPlayers(){
		Collection<Player> players = getPlayers();
		players.clear();
		return players;
	}
}
