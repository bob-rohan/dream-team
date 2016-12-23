package rohan.dreamteam.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.Goalkeeper;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.web.DreamTeamController;

@Service
public class DreamTeamServiceMock implements DreamTeamService{
	
	private final static Logger LOGGER = Logger.getLogger(DreamTeamController.class);
	
	private Collection<Player> players;
	
	public Collection<Player> getPlayers(){
		
		if (players == null){
			generatePlayers();
		}
		
		return players;
			
	}
	
	// TODO: Refactor - SRP violation
	private void generatePlayers(){
		players = new ArrayList<Player>();

		players.add(getPlayer());
		players.add(getPlayer());
		players.add(getPlayer());
	}
	
	private Player getPlayer(){
		Goalkeeper goalkeeper = new Goalkeeper();
		goalkeeper.setVisibility(true);
		goalkeeper.setId(0);
		goalkeeper.setName("One");
		goalkeeper.setNowCost(new BigDecimal(1.7f));
		goalkeeper.setTotalPoints(38);
		return goalkeeper;
	}
		
}
