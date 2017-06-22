package rohan.dreamteam.transformers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.GameweekStatistics;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.domain.PlayerFactory;
import rohan.dreamteam.domain.Position;
import rohan.dreamteam.fpldomain.initialdata.Element;
import rohan.dreamteam.fpldomain.initialdata.ElementType;
import rohan.dreamteam.fpldomain.initialdata.InitialDataRoot;
import rohan.dreamteam.fpldomain.initialdata.Team;
import rohan.dreamteam.fpldomain.playerdata.History;
import rohan.dreamteam.fpldomain.playerdata.PlayerDataRoot;

@Service
public class DreamTeamTransformerImpl implements DreamTeamTransformer {

	@Autowired
	DataConverter dataConverter;

	@Override
	public Collection<Player> transformStringToPlayers(final String playersData) {

		InitialDataRoot initialDataRoot = dataConverter.convertJsonToInitialDataRoot(playersData);

		return getPlayers(initialDataRoot);

	}

	@Override
	public Collection<GameweekStatistics> transformStringToGameweekStatistics(final String playerData) {

		PlayerDataRoot playerDataRoot = dataConverter.convertJsonToPlayerDataRoot(playerData);

		return getGameweeks(playerDataRoot);
	}

	private Map<Integer, rohan.dreamteam.domain.Team> getTeams(final InitialDataRoot initialDataRoot) {

		Map<Integer, rohan.dreamteam.domain.Team> teams = new HashMap<>();
		for (Team team : initialDataRoot.getTeams()) {

			rohan.dreamteam.domain.Team dreamTeamTeam = new rohan.dreamteam.domain.Team();

			dreamTeamTeam.setCode(team.getCode());
			dreamTeamTeam.setName(team.getName());

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

	private Collection<Player> getPlayers(final InitialDataRoot initialDataRoot) {

		final Collection<Player> players = new ArrayList<>();

		final Map<Integer, rohan.dreamteam.domain.Team> teams = getTeams(initialDataRoot);

		final Map<Integer, Position> positions = getPositions(initialDataRoot);

		final Collection<Element> elements = initialDataRoot.getElements();

		for (Element element : elements) {
			Position position = positions.get(element.getElement_type());
			Player player = PlayerFactory.getPlayer(position.getShortName());

			player.setId(element.getId());
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

}