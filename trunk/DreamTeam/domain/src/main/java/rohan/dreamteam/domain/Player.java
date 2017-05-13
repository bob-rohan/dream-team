package rohan.dreamteam.domain;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author Admin
 *
 */
public class Player {

	private int id;

	private int totalPoints;

	private int totalYellowCards;

	private String name;

	private Team team;

	private Position position;

	private BigDecimal nowCost;

	private Collection<GameweekStatistics> gameweeksStatistics;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public BigDecimal getNowCost() {
		return nowCost;
	}

	public void setNowCost(BigDecimal nowCost) {
		this.nowCost = nowCost;
	}

	public int getTotalYellowCards() {
		return totalYellowCards;
	}

	public void setTotalYellowCards(int totalYellowCards) {
		this.totalYellowCards = totalYellowCards;
	}

	public Collection<GameweekStatistics> getGameweeksStatistics() {
		return gameweeksStatistics;
	}

	public void setGameweeksStatistics(Collection<GameweekStatistics> gameweeksStatistics) {
		this.gameweeksStatistics = gameweeksStatistics;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
