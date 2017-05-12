package rohan.dreamteam.web.playerhelp;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rohan.dreamteam.domain.Player;
import rohan.dreamteam.services.DreamTeamService;

@RestController
public class PlayerHelpController {

	private final static Logger LOGGER = LoggerFactory.getLogger(PlayerHelpController.class);

	@Autowired
	@Qualifier("dreamTeamServiceImpl")
	private DreamTeamService dreamTeamService;

	@RequestMapping(path = "getPlayers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Player> getPlayers() {

		Collection<Player> players = dreamTeamService.getPlayers();

		return players;
	}

}
