package dreamteam.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dreamteam.domain.Player;
import dreamteam.domain.PlayerFactory;
import dreamteam.fpl.initialData.Element;
import dreamteam.fpl.initialData.ElementType;
import dreamteam.fpl.initialData.InitialDataRoot;
import dreamteam.fpl.initialData.Team;
import dreamteam.fpl.playerData.History;
import dreamteam.fpl.playerData.PlayerDataRoot;
import dreamteam.web.DreamTeamController;

@Service
public class DreamTeamServiceImpl implements DreamTeamService{
	
	private final static Logger LOGGER = Logger.getLogger(DreamTeamController.class);
	
	private final static String DREAM_TEAM_URL_INITIAL_DATA = "https://fantasy.premierleague.com/drf/bootstrap-static";
	
	private final static String DREAM_TEAM_URL_PLAYER_DATA = "https://fantasy.premierleague.com/drf/element-summary/";
	
	private final static int MINIMUM_GAMES = 4;
	
	private final static int MINIMUM_MINUTES = 60;
	
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

		try {
			final String initialData = getInitialData();

			ObjectMapper mapper = new ObjectMapper();

			mapper.enable(SerializationFeature.INDENT_OUTPUT);
						
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			InitialDataRoot initialDataRoot = mapper.readValue(initialData, InitialDataRoot.class);
			
			// adapt/transform initial data to domain object
			Map<Integer, String> teams = new HashMap<>();
			for(Team team : initialDataRoot.getTeams()){
				teams.put(team.getCode(), team.getName());
			}
			
			Map<Integer, String> positions = new HashMap<>();
			for(ElementType position : initialDataRoot.getElement_types()){
				positions.put(position.getId(), position.getSingular_name_short());
			}
			
			for(Element element : initialDataRoot.getElements()){
				String positionShortName = positions.get(element.getElement_type());
				Player player = PlayerFactory.getPlayer(positionShortName);
				
				player.setId(element.getId());
				player.setName(element.getWeb_name());
								
				int intNowCost = element.getNow_cost();
				BigDecimal nowCost = new BigDecimal(intNowCost);
				nowCost = nowCost.divide(new BigDecimal(10), 1, RoundingMode.HALF_UP);
				player.setNowCost(nowCost);
				player.setTotalPoints(element.getTotal_points());
				player.setTeam(teams.get(element.getTeam_code()));
				player.setPosition(positions.get(element.getElement_type()));
				
				players.add(player);
			}
			
			for(Player player : players){
				final String playerData = getPlayerString(player.getId());
				Object prettyPlayerData = mapper.readValue(playerData, Object.class);
				LOGGER.trace(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(prettyPlayerData));
				
				PlayerDataRoot playerDataRoot = mapper.readValue(playerData, PlayerDataRoot.class);
				
				player.setStandardDeviation(getStandardDeviation(playerDataRoot.getHistory()));
				player.setGamesPlayed(getGamesPlayed(playerDataRoot.getHistory()));
				player.setVisibility(getPlayerVisibility(playerDataRoot.getHistory()));
			}	
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private BigDecimal getStandardDeviation(Collection<History> histories){
		SummaryStatistics summaryStatistics = new SummaryStatistics();
		
		for (History history : histories){
			summaryStatistics.addValue(new Double(history.getTotal_points()));
		}
		
		double standardDeviation = summaryStatistics.getStandardDeviation();
		
		BigDecimal roundedStandardDeviation = new BigDecimal(standardDeviation);
		
		roundedStandardDeviation = roundedStandardDeviation.setScale(2, RoundingMode.HALF_UP);
		
		return roundedStandardDeviation;
	}
	
	private boolean getPlayerVisibility(Collection<History> histories){
		
		boolean visibility = true;
		
		visibility = isMetMinimumGames(histories);
		
		if(visibility){
			visibility = isMetMinimumMinutes(histories);
		}
		
		return visibility;
	}
	
	private boolean isMetMinimumMinutes(Collection<History> histories){
		boolean metMinimumMinutes = true;
		
		/**
		 * TODO: Shit implementation
		 */
		if(histories instanceof List<?>){
			Collections.reverse((List<?>) histories);
		}
		
		int gameweeksChecked = 0;
		for(History history : histories){
			if(history.getMinutes() < MINIMUM_MINUTES){
				metMinimumMinutes = false;
				break;
			}
			if(gameweeksChecked >= MINIMUM_GAMES){
				break;
			}
			gameweeksChecked++;
		}
	
		return metMinimumMinutes;
	}
	
	private boolean isMetMinimumGames(Collection<History> histories){
		boolean metMinimumGames = true;
		
		if(histories.size() < MINIMUM_GAMES){
			metMinimumGames = false;
		}
		
		return metMinimumGames;
	}
	
	private int getGamesPlayed(Collection<History> histories){
		int gamesPlayed = 0;
		for(History history : histories){
			if(history.getMinutes() > 0){
				gamesPlayed++;
			}
		}
		return gamesPlayed;
	}
	
	private String getInitialData() throws IOException{
		HttpClient client = new HttpClient();
		
		HttpMethod method = new GetMethod(DREAM_TEAM_URL_INITIAL_DATA);
		
		client.executeMethod(method);
		
		final String responseBody = method.getResponseBodyAsString();
		
		return responseBody;
	}
	
	private Player getPlayerById(int playerId) throws IOException{
		
		final String playerString = getPlayerString(playerId);
		
		System.out.println(playerString);
		
		return null;
	}
	
	private String getPlayerString(int playerId) throws IOException{
		
		HttpClient client = new HttpClient();
		
		HttpMethod method = new GetMethod(DREAM_TEAM_URL_PLAYER_DATA + playerId);
		
		client.executeMethod(method);
		
		final String responseBody = method.getResponseBodyAsString();
		
		return responseBody;
	}
	
}
