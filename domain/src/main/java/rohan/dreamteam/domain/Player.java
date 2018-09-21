package rohan.dreamteam.domain;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.annotation.Id;

/**
 * @author Admin
 *
 */
public class Player {

	@Id
	private String id;
	
	private int fplId;

	private int totalPoints;

	private int totalYellowCards;

	private String name;

	private Team team;

	private Position position;

	private BigDecimal nowCost;
	
	private int likelihoodOfPriceChange;

	private Collection<GameweekStatistics> gameweeksStatistics;
	
	private Collection<Fixture> fixtures;
	
	private Fitness fitness;
	
	private Boolean selected;

	public int getFplId() {
		return fplId;
	}

	public void setFplId(int fplId) {
		this.fplId = fplId;
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

	public Collection<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(Collection<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	public int getLikelihoodOfPriceChange() {
		return likelihoodOfPriceChange;
	}

	public void setLikelihoodOfPriceChange(int likelihoodOfPriceChange) {
		this.likelihoodOfPriceChange = likelihoodOfPriceChange;
	}

	public Fitness getFitness() {
		return fitness;
	}

	public void setFitness(Fitness fitness) {
		this.fitness = fitness;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
