package rohan.dreamteam.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.Goalkeeper;
import rohan.dreamteam.domain.Player;

@Service
public class DreamTeamServiceMock implements DreamTeamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DreamTeamServiceMock.class);

	private Collection<Player> players;

	@Override
	public Collection<Player> getPlayers() {

		LOGGER.info("Getting players...");
		if (players == null) {
			generatePlayers();
		}

		return players;

	}

	// TODO: Refactor - SRP violation
	private void generatePlayers() {
		players = new ArrayList<>();

		players.add(getPlayer());
		players.add(getPlayer());
		players.add(getPlayer());
	}

	private Player getPlayer() {
		Goalkeeper goalkeeper = new Goalkeeper();
		goalkeeper.setId(0);
		goalkeeper.setName("One");
		goalkeeper.setNowCost(BigDecimal.valueOf(1.7f).setScale(1, RoundingMode.HALF_UP));
		goalkeeper.setTotalPoints(38);
		// TODO: Set gameweeks
		return goalkeeper;
	}

}
