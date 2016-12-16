package dreamteam.web;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dreamteam.domain.Defender;
import dreamteam.domain.Forward;
import dreamteam.domain.Goalkeeper;
import dreamteam.domain.Midfielder;
import dreamteam.domain.Player;
import dreamteam.services.DreamTeamService;

@Controller
public class DreamTeamController {

	private final static Logger LOGGER = Logger.getLogger(DreamTeamController.class);
	
	private final static String HOME_VIEW = "home";
	
	@Autowired
	@Qualifier("dreamTeamServiceImpl")
	private DreamTeamService dreamTeamService;
	
	@RequestMapping("/home")
	public String home(DreamTeamCommand dreamTeamCommand, Model model){
		
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
		
		Collection<Player> dreamTeam = getDreamTeam(dreamTeamCommand);
		dreamTeamCommand.setDreamTeam(dreamTeam);
		
		return HOME_VIEW;
	}
	
	private Collection<Goalkeeper> getGoalkeepers(Collection<Player> players){
		Collection<Goalkeeper> goalkeepers = new ArrayList<>();
		
		for(Player player : players){
			if (player.isVisibility() && player instanceof Goalkeeper){
				goalkeepers.add((Goalkeeper) player);
			}
		}
		
		return goalkeepers;
	}
	
	private Collection<Defender> getDefenders(Collection<Player> players){
		Collection<Defender> defenders = new ArrayList<>();
		
		for(Player player : players){
			if (player.isVisibility() && player instanceof Defender){
				defenders.add((Defender) player);
			}
		}
		
		return defenders;
	}
	
	private Collection<Midfielder> getMidfielders(Collection<Player> players){
		Collection<Midfielder> midfielers = new ArrayList<>();
		
		for(Player player : players){
			if (player.isVisibility() && player instanceof Midfielder){
				midfielers.add((Midfielder) player);
			}
		}
		
		return midfielers;
	}
	
	private Collection<Forward> getForwards(Collection<Player> players){
		Collection<Forward> forwards = new ArrayList<>();
		
		for(Player player : players){
			if (player.isVisibility() && player instanceof Forward){
				forwards.add((Forward) player);
			}
		}
		
		return forwards;
	}
	
	private Collection<Player> getDreamTeam(DreamTeamCommand dreamTeamCommand){
		
		Collection<Player> dreamTeam = new ArrayList<>();
		
		int goalkeepers = 2;
		int defenders = 5;
		int midfielders = 5;
		int forwards = 3;
		
		/*
		 * TODO // drop by re-factoring out sort to the view, rewrite required.
		 */
		
		return dreamTeam;
	}
}
