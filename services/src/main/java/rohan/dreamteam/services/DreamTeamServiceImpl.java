package rohan.dreamteam.services;

import java.util.Collection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.Player;
import rohan.dreamteam.transformers.DreamTeamTransformer;

@Service
@Profile("!mock")
public class DreamTeamServiceImpl implements DreamTeamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DreamTeamServiceImpl.class);

	private static final String DREAM_TEAM_URL_INITIAL_DATA = "https://fantasy.premierleague.com/drf/bootstrap-static";

	private static final String DREAM_TEAM_URL_PLAYER_DATA = "https://fantasy.premierleague.com/drf/element-summary/";
	
	private static final String FPL_STATISTICS = "http://www.fplstatistics.co.uk/Home/IndexWG";
	
	private static final String FPL_STATISTICS_PAGE_PARAM = "gridPriceData_inside";

	@Autowired
	private HttpClientService httpClientService;

	@Autowired
	private DreamTeamTransformer dreamTeamTransformer;

	private Collection<Player> generatedPlayers;

	@Override
	public Collection<Player> getPlayers() {

		if (generatedPlayers == null) {
			refreshPlayers();
		}

		return generatedPlayers;

	}
	
	@Override
	public Collection<Player> refreshPlayers() {

		generatedPlayers = generatePlayers();

		return generatedPlayers;
	}

	private Collection<Player> generatePlayers() {

		String priceChangeHtml = httpClientService.getDataString(FPL_STATISTICS);
		
		final Collection<Player> priceChangePlayers = dreamTeamTransformer.transformPriceChangeStringToPlayers(priceChangeHtml);
		
		int pageNumber = 1;
		
		while(containsMorePriceChangePages(priceChangeHtml)){
			
			pageNumber++;
			
			priceChangeHtml = httpClientService.getDataString(FPL_STATISTICS + "?" + FPL_STATISTICS_PAGE_PARAM + "=" + pageNumber);	
			
			priceChangePlayers.addAll(dreamTeamTransformer.transformPriceChangeStringToPlayers(priceChangeHtml));
		}
		
		final String intialDataResponse = httpClientService.getDataString(DREAM_TEAM_URL_INITIAL_DATA);

		Collection<Player> players = dreamTeamTransformer.transformStringToPlayers(intialDataResponse);
		
		for (Player player : players) {
			LOGGER.info("ID: {}, Name: {}", player.getId(), player.getName());

			final String playerDataResponse = httpClientService
					.getDataString(DREAM_TEAM_URL_PLAYER_DATA + player.getId());
			
			dreamTeamTransformer.transformStringToPlayer(playerDataResponse, player);
			
			applyPriceChangeStats(priceChangePlayers, player);
			
			LOGGER.trace("Player = {}", player);
		}

		return players;
	}
	
	private boolean containsMorePriceChangePages(String priceChangeHtml){
		
		Document document = Jsoup.parse(priceChangeHtml);
		
		org.jsoup.select.Elements table = document.getElementsByClass("webgrid");

		org.jsoup.nodes.Element tableFooter = table.first().child(1);
		
		org.jsoup.nodes.Element tableFooterRow = tableFooter.child(0);
		
		org.jsoup.nodes.Element tableFooterRowData = tableFooterRow.child(0);
		
		boolean containsMorePriceChangePages = false;
		
		for (org.jsoup.nodes.Element element : tableFooterRowData.children()) {
			if(element.text().contains(">")){
				containsMorePriceChangePages = true;
				break;
			}
		}
		
		return containsMorePriceChangePages;
		
	}
	
	private void applyPriceChangeStats(Collection<Player> priceChangePlayers, final Player player){
		
		for(Player priceChangePlayer : priceChangePlayers){
			if(priceChangePlayer.getName().equalsIgnoreCase(player.getName()) && priceChangePlayer.getTeam().getName().equalsIgnoreCase(player.getTeam().getName())){
				player.setLikelihoodOfPriceChange(priceChangePlayer.getLikelihoodOfPriceChange());
			}
		}
		
	}
}
