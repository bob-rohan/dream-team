package rohan.dreamteam.restserver.playerhelp;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rohan.dreamteam.domain.Player;
import rohan.dreamteam.services.DreamTeamService;

@RestController
public class PlayerHelpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerHelpController.class);

	@Autowired
	@Qualifier("dreamTeamServiceImpl")
	private DreamTeamService dreamTeamService;

	@GetMapping(path = "getPlayers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Player> getPlayers() {

		// TODO: Insert method name programmatically.
		LOGGER.info("Received getPlayers request");

		return dreamTeamService.getPlayers();
	}

}