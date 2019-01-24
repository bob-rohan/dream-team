package rohan.dreamteam.services.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import rohan.dreamteam.services.DreamTeamService;

@Service
@Profile("!mock")
public class RefreshPlayersTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(RefreshPlayersTask.class);

	@Autowired
	private DreamTeamService dreamTeamService;

	// @Scheduled(fixedRateString = "${refreshPlayers.fixedRate}")
	public void refreshPlayers() {
		LOGGER.info("Starting refresh players task");

		dreamTeamService.refreshPlayers();
	}
}
