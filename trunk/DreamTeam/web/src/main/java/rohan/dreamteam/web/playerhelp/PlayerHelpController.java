package rohan.dreamteam.web.playerhelp;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import rohan.dreamteam.domain.Defender;
import rohan.dreamteam.domain.Forward;
import rohan.dreamteam.domain.Goalkeeper;
import rohan.dreamteam.domain.Midfielder;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.services.DreamTeamService;

@Controller
public class PlayerHelpController {

	private final static Logger LOGGER = LoggerFactory.getLogger(PlayerHelpController.class);

	private final static String HOME_VIEW = "home";

	@Autowired
	@Qualifier("dreamTeamServiceImpl")
	private DreamTeamService dreamTeamService;

	@RequestMapping("/home")
	public String home(PlayerHelpCommand dreamTeamCommand, Model model) {

		Collection<Player> players = dreamTeamService.getPlayers();
		dreamTeamCommand.setPlayers(players);

		Collection<Goalkeeper> goalkeepers = getGoalkeepers(players);
		dreamTeamCommand.setGoalkeepers(goalkeepers);

		Collection<Defender> defenders = getDefenders(players);
		dreamTeamCommand.setDefenders(defenders);

		Collection<Midfielder> midfielders = getMidfielders(players);
		dreamTeamCommand.setMidfielders(midfielders);

		Collection<Forward> forwards = getForwards(players);
		dreamTeamCommand.setForwards(forwards);

		return HOME_VIEW;
	}

	private Collection<Goalkeeper> getGoalkeepers(Collection<Player> players) {
		Collection<Goalkeeper> goalkeepers = new ArrayList<>();

		for (Player player : players) {
			if (player.isVisibility() && player instanceof Goalkeeper) {
				goalkeepers.add((Goalkeeper) player);
			}
		}

		return goalkeepers;
	}

	private Collection<Defender> getDefenders(Collection<Player> players) {
		Collection<Defender> defenders = new ArrayList<>();

		for (Player player : players) {
			if (player.isVisibility() && player instanceof Defender) {
				defenders.add((Defender) player);
			}
		}

		return defenders;
	}

	private Collection<Midfielder> getMidfielders(Collection<Player> players) {
		Collection<Midfielder> midfielers = new ArrayList<>();

		for (Player player : players) {
			if (player.isVisibility() && player instanceof Midfielder) {
				midfielers.add((Midfielder) player);
			}
		}

		return midfielers;
	}

	private Collection<Forward> getForwards(Collection<Player> players) {
		Collection<Forward> forwards = new ArrayList<>();

		for (Player player : players) {
			if (player.isVisibility() && player instanceof Forward) {
				forwards.add((Forward) player);
			}
		}

		return forwards;
	}
}
