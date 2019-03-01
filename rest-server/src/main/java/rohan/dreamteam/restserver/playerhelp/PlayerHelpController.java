package rohan.dreamteam.restserver.playerhelp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rohan.dreamteam.domain.Configuration;
import rohan.dreamteam.domain.Gameweek;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.services.DreamTeamService;

@RestController
public class PlayerHelpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerHelpController.class);

	@Autowired
	private DreamTeamService dreamTeamService;

	@GetMapping(path = "getPlayers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Player> getPlayers() {

		// TODO: Insert method name programmatically.
		LOGGER.info("Received getPlayers request");

		return dreamTeamService.getPlayers();
	}
	
	@GetMapping(path = "refreshPlayers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Player> refreshPlayers() {

		// TODO: Insert method name programmatically.
		LOGGER.info("Received refreshPlayers request");

		return dreamTeamService.refreshPlayers();
	}
	
	@PostMapping(path = "setSelected")
	public void setSelected(@RequestParam int fplId, @RequestParam boolean selected){
		
		LOGGER.info("Setting selected status {}, for player {}", selected, fplId);
	
		dreamTeamService.setSelected(fplId, selected);
		
	}
	
	@GetMapping(path = "getConfiguration", produces = MediaType.APPLICATION_JSON_VALUE)
	public Configuration getConfiguration() {
		LOGGER.info("Getting configuration");
		
		return dreamTeamService.getConfiguration();
	}
	
	@GetMapping(path = "getGameweeks", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Gameweek> getGameweeks() {
		LOGGER.info("Getting gameweeks");
		
		return dreamTeamService.getGameweeks();
	}
	
}
