package rohan.dreamteam.transformers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import rohan.dreamteam.domain.Gameweek;
import rohan.dreamteam.domain.Player;
import rohan.dreamteam.domain.PlayerFactory;
import rohan.dreamteam.domain.Position;
import rohan.dreamteam.fpldomain.initialData.Element;
import rohan.dreamteam.fpldomain.initialData.ElementType;
import rohan.dreamteam.fpldomain.initialData.InitialDataRoot;
import rohan.dreamteam.fpldomain.initialData.Team;
import rohan.dreamteam.fpldomain.playerData.History;
import rohan.dreamteam.fpldomain.playerData.PlayerDataRoot;

@Service
public class DreamTeamTransformerImpl implements DreamTeamTransformer {

	public Map<Integer, rohan.dreamteam.domain.Team> getTeams(final InitialDataRoot initialDataRoot) {

		Map<Integer, rohan.dreamteam.domain.Team> teams = new HashMap<>();
		for (Team team : initialDataRoot.getTeams()) {

			rohan.dreamteam.domain.Team dreamTeamTeam = new rohan.dreamteam.domain.Team();

			dreamTeamTeam.setCode(team.getCode());
			dreamTeamTeam.setName(team.getName());

			teams.put(dreamTeamTeam.getCode(), dreamTeamTeam);
		}

		return teams;
	}

	public Map<Integer, Position> getPositions(final InitialDataRoot initialDataRoot) {

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

		final Collection<Player> players = new ArrayList<Player>();

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

	public Collection<Gameweek> getGameweeks(final PlayerDataRoot playerDataRoot) {

		final Collection<History> histories = playerDataRoot.getHistory();

		final Collection<Gameweek> gameweeks = new ArrayList<>();

		for (final History history : histories) {
			Gameweek gameweek = new Gameweek();

			gameweek.setTotalPoints(history.getTotal_points());
			gameweek.setKickoff(history.getKickoff_time());
			gameweek.setMinutes(history.getMinutes());
			gameweek.setGameweek(history.getRound());
			gameweek.setYellowCards(history.getYellow_cards());

			gameweeks.add(gameweek);

		}

		return gameweeks;
	}
}
