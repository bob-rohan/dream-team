package rohan.dreamteam.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * TODO: Fix mixing datastructure with behaviour
 * 
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

	private Collection<Gameweek> gameweeks;

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

	public Collection<Gameweek> getGameweeks() {
		return gameweeks;
	}

	public void setGameweeks(Collection<Gameweek> gameweeks) {
		this.gameweeks = gameweeks;
	}

	public int getGamesPlayed() {
		int gamesPlayed = 0;
		for (Gameweek gameweek : gameweeks) {
			if (gameweek.getMinutes() > 0) {
				gamesPlayed++;
			}
		}
		return gamesPlayed;
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
		boolean visibility = true;

		visibility = isMetMinimumGames();

		if (visibility) {
			visibility = isMetMinimumMinutes();
		}

		return visibility;
	}

	/**
	 * TODO: Refactor, shit impl. TODO: Test method
	 * 
	 * @return
	 */
	private boolean isMetMinimumMinutes() {
		boolean metMinimumMinutes = true;

		Comparator<Gameweek> comparator = new Comparator<Gameweek>() {

			@Override
			public int compare(Gameweek o1, Gameweek o2) {
				return o2.getKickoff().compareTo(o1.getKickoff());
			}

		};

		SortedSet<Gameweek> reverseSortedGameweeks = new TreeSet<>(comparator);
		reverseSortedGameweeks.addAll(gameweeks);

		int gameweeksChecked = 0;
		for (Gameweek gameweek : reverseSortedGameweeks) {
			if (gameweek.getMinutes() < 60) {
				metMinimumMinutes = false;
				break;
			}
			if (gameweeksChecked >= 4) {
				break;
			}
			gameweeksChecked++;
		}

		return metMinimumMinutes;
	}

	/**
	 * TODO: Refactor, shit impl.
	 * 
	 * @return
	 */
	private boolean isMetMinimumGames() {
		boolean metMinimumGames = true;

		if (gameweeks.size() < 4) {
			metMinimumGames = false;
		}

		return metMinimumGames;
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
				// TODO: auto-generated catch block;
				e.printStackTrace();
			}
		}
		return response;
	}

	/**
	 * TODO: Refactor method, looks a bit of a mess currently.
	 * 
	 * @return
	 */
	public BigDecimal getStandardDeviation() {
		SummaryStatistics summaryStatistics = new SummaryStatistics();

		for (Gameweek gameweek : gameweeks) {
			summaryStatistics.addValue(new Double(gameweek.getTotalPoints()));
		}

		double standardDeviation = summaryStatistics.getStandardDeviation();

		BigDecimal roundedStandardDeviation = null;

		try {

			roundedStandardDeviation = new BigDecimal(standardDeviation);

		} catch (Exception e) {
			e.printStackTrace();
		}
		roundedStandardDeviation = roundedStandardDeviation.setScale(2, RoundingMode.HALF_UP);

		return roundedStandardDeviation;
	}

	public boolean isYellowCardBanAlert() {
		boolean isYellowCardsForSeasonThresholdReached = isYellowCardsForSeasonThresholdReached();

		boolean isYellowCardsForHalfYearThresholdReached = isYellowCardsForHalfYearThresholdReached();

		boolean isYellowCardBanAlert = isYellowCardsForSeasonThresholdReached
				|| isYellowCardsForHalfYearThresholdReached;

		return isYellowCardBanAlert;
	}

	/**
	 * Refactor magic numbers;
	 * 
	 * @param player
	 * @return
	 */
	private boolean isYellowCardsForSeasonThresholdReached() {
		return totalYellowCards == 9 || totalYellowCards == 14;
	}

	/**
	 * Refactor threshold.
	 * 
	 * @param histories
	 * @return
	 */
	private boolean isYellowCardsForHalfYearThresholdReached() {
		NavigableMap<Date, Integer> sortedHistories = new TreeMap<>();

		for (Gameweek gameweek : gameweeks) {
			if (gameweek.getYellowCards() != 0) {
				sortedHistories.put(gameweek.getKickoff(), gameweek.getYellowCards());
			}
		}

		final Date threshold = new Date("12/31/2016");

		final Date now = new Date();

		boolean isYellowCardsForHalfYearThresholdReached = false;

		Map<Date, Integer> halfYearYellowCardHistory = null;
		if (now.before(threshold)) {
			halfYearYellowCardHistory = sortedHistories.headMap(threshold, true);
		} else {
			halfYearYellowCardHistory = sortedHistories.tailMap(threshold, false);
		}

		isYellowCardsForHalfYearThresholdReached = (halfYearYellowCardHistory.size() == 4);

		return isYellowCardsForHalfYearThresholdReached;
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
