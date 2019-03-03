package rohan.dreamteam.services;

import java.util.Collection;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.Configuration;
import rohan.dreamteam.domain.Gameweek;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.domain.Team;
import rohan.dreamteam.fpldomain.initialdata.InitialDataRoot;
import rohan.dreamteam.services.dao.ConfigurationRepository;
import rohan.dreamteam.services.dao.GameweekRepository;
import rohan.dreamteam.services.dao.PlayerRepository;
import rohan.dreamteam.services.dao.TeamRepository;
import rohan.dreamteam.transformers.DreamTeamTransformer;

@Service
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

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Autowired
	private GameweekRepository gameweekRepository;
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public Iterable<Player> getPlayers() {

		return playerRepository.findAll();

	}

	@Override
	public void setSelected(int fplId, boolean selected) {

		Player player = playerRepository.findByFplId(fplId);

		player.setSelected(selected);

		playerRepository.save(player);
	}

	@Override
	public Iterable<Player> refreshPlayers() {

		refreshPlayerData();

		return playerRepository.findAll();
	}

	private void refreshPlayerData() {

		String priceChangeHtml = httpClientService.getDataString(FPL_STATISTICS);

		final Collection<Player> priceChangePlayers = dreamTeamTransformer
				.transformPriceChangeStringToPlayers(priceChangeHtml);

		int pageNumber = 1;

		while (containsMorePriceChangePages(priceChangeHtml)) {

			pageNumber++;

			priceChangeHtml = httpClientService
					.getDataString(FPL_STATISTICS + "?" + FPL_STATISTICS_PAGE_PARAM + "=" + pageNumber);

			priceChangePlayers.addAll(dreamTeamTransformer.transformPriceChangeStringToPlayers(priceChangeHtml));
		}

		final String intialDataResponse = httpClientService.getDataString(DREAM_TEAM_URL_INITIAL_DATA);

		InitialDataRoot initialData = dreamTeamTransformer.transformInitialData(intialDataResponse);
		
		upsertGameweek(dreamTeamTransformer.getGameweeks(initialData));
		
		Iterable<Configuration> configurations = configurationRepository.findAll();

		Configuration configuration = (configurations.iterator().hasNext())
				? dreamTeamTransformer.getConfiguration(initialData, configurations.iterator().next())
				: dreamTeamTransformer.getConfiguration(initialData);

		configurationRepository.save(configuration);
				
		Collection<Player> players = dreamTeamTransformer.getPlayers(initialData);

		for (Player player : players) {
			LOGGER.info("ID: {}, Name: {}", player.getFplId(), player.getName());

			final String playerDataResponse = httpClientService
					.getDataString(DREAM_TEAM_URL_PLAYER_DATA + player.getFplId());

			dreamTeamTransformer.transformStringToPlayer(playerDataResponse, player);

			applyPriceChangeStats(priceChangePlayers, player);

			LOGGER.trace("Player = {}", player);

			upsertPlayer(player);
		}
		
		upsertTeam(initialData.getTeams(), players);
	}

	// TODO: refactor to DAO
	private void upsertPlayer(final Player player) {

		Player persistedPlayer = playerRepository.findByFplId(player.getFplId());

		if (persistedPlayer != null) {
			player.setId(persistedPlayer.getId());

			// TODO: think about implementation
			player.setSelected(persistedPlayer.getSelected());
		}

		playerRepository.save(player);
	}
	
	private void upsertGameweek(final Collection<Gameweek> gameweeks) {
		gameweeks.forEach(gameweek -> {
			
			if(gameweekRepository.findByGameweek(gameweek.getGameweek()) == null) {
				gameweekRepository.save(gameweek);
			}
			
		});
	}
	
	private void upsertTeam(final Collection<rohan.dreamteam.fpldomain.initialdata.Team> teams, final Collection<Player> players){
		
		teams.forEach(team -> {
	
			Optional<Player> playerFromTeamOpt = players.stream().filter(player -> team.getName().equalsIgnoreCase(player.getTeam().getName())).findFirst();
				
			if (playerFromTeamOpt.isPresent()){
				Team upsertTeam = (teamRepository.findByName(team.getName())) == null ? new Team() : teamRepository.findByName(team.getName());
				
				upsertTeam.setName(team.getName());
				upsertTeam.setFixtures(playerFromTeamOpt.get().getFixtures());
				
				teamRepository.save(upsertTeam);
					
			}
			
		});
		
	}

	private boolean containsMorePriceChangePages(String priceChangeHtml) {

		Document document = Jsoup.parse(priceChangeHtml);

		org.jsoup.select.Elements table = document.getElementsByClass("webgrid");

		org.jsoup.nodes.Element tableFooter = table.first().child(1);

		org.jsoup.nodes.Element tableFooterRow = tableFooter.child(0);

		org.jsoup.nodes.Element tableFooterRowData = tableFooterRow.child(0);

		boolean containsMorePriceChangePages = false;

		for (org.jsoup.nodes.Element element : tableFooterRowData.children()) {
			if (element.text().contains(">")) {
				containsMorePriceChangePages = true;
				break;
			}
		}

		return containsMorePriceChangePages;

	}

	private void applyPriceChangeStats(Collection<Player> priceChangePlayers, final Player player) {

		for (Player priceChangePlayer : priceChangePlayers) {
			if (priceChangePlayer.getName().equalsIgnoreCase(player.getName())
					&& priceChangePlayer.getTeam().getName().equalsIgnoreCase(player.getTeam().getName())) {
				player.setLikelihoodOfPriceChange(priceChangePlayer.getLikelihoodOfPriceChange());
			}
		}

	}

	@Override
	public Configuration getConfiguration() {
		// Potential NPE
		return configurationRepository.findAll().iterator().next();
	}

	@Override
	public Iterable<Gameweek> getGameweeks() {
		return gameweekRepository.findAll();
	}

	@Override
	public Iterable<Team> getTeams() {
		return teamRepository.findAll();
	}
}
