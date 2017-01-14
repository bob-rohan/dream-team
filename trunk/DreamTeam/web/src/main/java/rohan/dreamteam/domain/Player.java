package rohan.dreamteam.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Player {

	private int id;

	private String name;

	private int totalPoints;

	private int totalYellowCards;

	private String team;

	private String position;

	private BigDecimal nowCost;

	private int gamesPlayed;

	private BigDecimal standardDeviation;

	// invariant of totalPoints / gamesPlayed - no setter required.
	private BigDecimal pointsPerGame;

	// invariant of pointsPerGame / nowCost - no setter required.
	private BigDecimal pointsPerGamePerMillion;

	private boolean visibility;

	private boolean yellowCardBanAlert;

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

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public BigDecimal getNowCost() {
		return nowCost;
	}

	public void setNowCost(BigDecimal nowCost) {
		this.nowCost = nowCost;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public BigDecimal getPointsPerGame() {
		int totalPoints = getTotalPoints();
		int gamesPlayed = getGamesPlayed();
		BigDecimal response = new BigDecimal(0);
		response.setScale(2);
		if (totalPoints > 0) {
			try {
				response = new BigDecimal(totalPoints);
				response = response.divide(new BigDecimal(gamesPlayed), 2, RoundingMode.HALF_UP);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return response;
		// return getTotalPoints() > 0 ? getTotalPoints() / getGamesPlayed() :
		// 0;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public BigDecimal getPointsPerGamePerMillion() {
		BigDecimal pointsPerGame = getPointsPerGame();
		BigDecimal nowCost = getNowCost();
		BigDecimal response = new BigDecimal(0);
		response.setScale(2);
		if (pointsPerGame.compareTo(response) == 1) {
			try {
				response = pointsPerGame;
				response = response.divide(nowCost, 2, RoundingMode.HALF_UP);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return response;
		// return getTotalPoints() > 0 ? getTotalPoints() / getGamesPlayed() :
		// 0;
	}

	public BigDecimal getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(BigDecimal standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public int getTotalYellowCards() {
		return totalYellowCards;
	}

	public void setTotalYellowCards(int totalYellowCards) {
		this.totalYellowCards = totalYellowCards;
	}

	public boolean isYellowCardBanAlert() {
		return yellowCardBanAlert;
	}

	public void setYellowCardBanAlert(boolean yellowCardBanAlert) {
		this.yellowCardBanAlert = yellowCardBanAlert;
	}

}
