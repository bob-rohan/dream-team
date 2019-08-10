package rohan.dreamteam.transformers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.Configuration;
import rohan.dreamteam.domain.Fitness;
import rohan.dreamteam.domain.Fixture;
import rohan.dreamteam.domain.Gameweek;
import rohan.dreamteam.domain.GameweekStatistics;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.domain.PlayerFactory;
import rohan.dreamteam.domain.Position;
import rohan.dreamteam.fpldomain.initialdata.Element;
import rohan.dreamteam.fpldomain.initialdata.ElementType;
import rohan.dreamteam.fpldomain.initialdata.Event;
import rohan.dreamteam.fpldomain.initialdata.InitialDataRoot;
import rohan.dreamteam.fpldomain.initialdata.Team;
import rohan.dreamteam.fpldomain.playerdata.FplFixture;
import rohan.dreamteam.fpldomain.playerdata.History;
import rohan.dreamteam.fpldomain.playerdata.PlayerDataRoot;
import rohan.dreamteam.services.dao.TeamRepository;
import rohan.dreamteam.transformers.exceptions.DreamTeamTransformationException;

@Service
public class DreamTeamTransformerImpl implements DreamTeamTransformer {

	private static final Logger LOGGER = LoggerFactory.getLogger(DreamTeamTransformerImpl.class);

	@Autowired
	DataConverter dataConverter;

	@Autowired
	TeamRepository teamRepository;

	@Override
	public InitialDataRoot transformInitialData(final String initialData) {

		return dataConverter.convertJsonToInitialDataRoot(initialData);

	}

	@Override
	public Player transformStringToPlayer(String playerData, Player player) {
		PlayerDataRoot playerDataRoot = dataConverter.convertJsonToPlayerDataRoot(playerData);

		player.setGameweeksStatistics(getGameweeks(playerDataRoot));

		player.setFixtures(getFixtures(playerDataRoot));

		return player;
	}

	private Map<Integer, rohan.dreamteam.domain.Team> getTeams(final InitialDataRoot initialDataRoot) {

		Map<Integer, rohan.dreamteam.domain.Team> teams = new HashMap<>();
		for (Team team : initialDataRoot.getTeams()) {

			rohan.dreamteam.domain.Team dreamTeamTeam = new rohan.dreamteam.domain.Team();

			dreamTeamTeam.setFplId(team.getId());
			dreamTeamTeam.setCode(team.getCode());
			dreamTeamTeam.setName(team.getName());
			dreamTeamTeam.setShortName(team.getShort_name());

			teams.put(dreamTeamTeam.getCode(), dreamTeamTeam);
		}

		return teams;
	}

	private Map<Integer, Position> getPositions(final InitialDataRoot initialDataRoot) {

		Map<Integer, Position> positions = new HashMap<>();
		for (ElementType position : initialDataRoot.getElement_types()) {

			Position dreamTeamPosition = new Position();
			dreamTeamPosition.setId(position.getId());
			dreamTeamPosition.setFullName(position.getSingular_name());
			dreamTeamPosition.setShortName(position.getSingular_name_short());

			positions.put(position.getId(), dreamTeamPosition);
		}

		return positions;
	}

	public Collection<Player> getPlayers(final InitialDataRoot initialDataRoot) {

		final Collection<Player> players = new ArrayList<>();

		final Map<Integer, rohan.dreamteam.domain.Team> teams = getTeams(initialDataRoot);

		final Map<Integer, Position> positions = getPositions(initialDataRoot);

		final Collection<Element> elements = initialDataRoot.getElements();

		for (Element element : elements) {
			Position position = positions.get(element.getElement_type());
			Player player = PlayerFactory.getPlayer(position.getShortName());

			player.setFplId(element.getId());
			player.setName(element.getWeb_name());
			player.setTotalYellowCards(element.getYellow_cards());

			int intNowCost = element.getNow_cost();
			BigDecimal nowCost = new BigDecimal(intNowCost);
			nowCost = nowCost.divide(new BigDecimal(10), 1, RoundingMode.HALF_UP);
			player.setNowCost(nowCost);
			player.setTotalPoints(element.getTotal_points());

			rohan.dreamteam.domain.Team team = teams.get(element.getTeam_code());
			player.setTeam(team);
			player.setPosition(position);

			Fitness fitness = new Fitness();
			fitness.setChanceOfPlaying(element.getChance_of_playing_next_round());
			fitness.setCommentary(element.getNews());
			player.setFitness(fitness);

			player.setSelected(false);

			players.add(player);
		}

		return players;
	}

	private Collection<GameweekStatistics> getGameweeks(final PlayerDataRoot playerDataRoot) {

		final Collection<History> histories = playerDataRoot.getHistory();

		final Collection<GameweekStatistics> gameweeksStatistics = new ArrayList<>();

		for (final History history : histories) {
			GameweekStatistics gameweekStatistics = new GameweekStatistics();

			gameweekStatistics.setTotalPoints(history.getTotal_points());
			gameweekStatistics.setKickoff(history.getKickoff_time());
			gameweekStatistics.setMinutes(history.getMinutes());
			gameweekStatistics.setGameweek(history.getRound());
			gameweekStatistics.setYellowCards(history.getYellow_cards());

			gameweeksStatistics.add(gameweekStatistics);

		}

		return gameweeksStatistics;
	}

	private Collection<Fixture> getFixtures(PlayerDataRoot playerDataRoot) {

		final Collection<FplFixture> fplFixtures = playerDataRoot.getFixtures();

		final Collection<Fixture> fixtures = new ArrayList<>();

		for (final FplFixture fplFixture : fplFixtures) {
			Fixture fixture = new Fixture();

			fixture.setGameweek(fplFixture.getEvent_name());
			fixture.setDifficulty(fplFixture.getDifficulty());
			fixture.setHome(fplFixture.isIs_home());

			int oponentTeamId = fplFixture.isIs_home() ? fplFixture.getTeam_a() : fplFixture.getTeam_h();

			rohan.dreamteam.domain.Team oponentTeam = teamRepository.findByFplId(oponentTeamId);

			if (oponentTeam != null) {
				fixture.setOponentName(oponentTeam.getName());
				fixture.setOponentShortName(oponentTeam.getShortName());
			}
			fixtures.add(fixture);
		}

		return fixtures;
	}

	public Configuration getConfiguration(InitialDataRoot initialDataRoot) {

		Configuration configuration = new Configuration();
		configuration.setNextGameweekId(initialDataRoot.getNextEvent());

		return configuration;
	}

	public Configuration getConfiguration(InitialDataRoot initialDataRoot, Configuration configuration) {

		configuration.setNextGameweekId(initialDataRoot.getNextEvent());

		return configuration;
	}

	public Collection<Gameweek> getGameweeks(InitialDataRoot initialDataRoot) {

		Collection<Gameweek> gameweeks = new ArrayList<>();

		for (Event event : initialDataRoot.getEvents()) {
			Gameweek gameweek = new Gameweek();
			gameweek.setGameweek(event.getId());
			gameweeks.add(gameweek);
		}

		return gameweeks;
	}

	/**
	 * This is a particularly fragile algorithm which involves parsing html. The
	 * host of this data doesn't want to provide a restful endpoint, and so
	 * "scraping" like this is the only option left to collect the data.
	 */
	@Override
	public Collection<Player> transformPriceChangeStringToPlayers(String priceChangeHtml) {
		List<Player> players = new ArrayList<>();

		try {
			LOGGER.trace(priceChangeHtml);

			Document document = Jsoup.parse(priceChangeHtml);

			org.jsoup.select.Elements table = document.getElementsByClass("webgrid");

			org.jsoup.nodes.Element tableBody = table.first().child(2);

			for (org.jsoup.nodes.Element tableRow : tableBody.children()) {

				final String teamName = tableRow.child(1).text();

				final String playerName = tableRow.child(0).text();

				final String likelihoodOfPriceChange = getLikelihoodOfPriceChange(tableRow.child(8));

				rohan.dreamteam.domain.Team team = new rohan.dreamteam.domain.Team();
				team.setName(teamName);

				Player player = new Player();

				player.setTeam(team);
				player.setName(playerName);
				player.setLikelihoodOfPriceChange(Double.valueOf(likelihoodOfPriceChange).intValue());

				players.add(player);
			}

		} catch (Exception e) {
			LOGGER.error("Unable to parse fpl statistics", e);
			throw new DreamTeamTransformationException("Unable to parse fpl statistics");
		}

		return players;
	}

	private String getLikelihoodOfPriceChange(org.jsoup.nodes.Element tableData) {
		String likelihoodofPriceChange;

		if (tableData.childNodeSize() != 0) {
			likelihoodofPriceChange = tableData.child(0).text();
		} else {
			likelihoodofPriceChange = tableData.text();
		}

		return likelihoodofPriceChange;
	}

}
