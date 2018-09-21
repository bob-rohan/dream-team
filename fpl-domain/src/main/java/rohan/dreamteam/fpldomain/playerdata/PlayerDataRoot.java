package rohan.dreamteam.fpldomain.playerdata;

import java.util.Collection;

public class PlayerDataRoot {

	private Collection<History> history;
	
	private Collection<FplFixture> fixtures;
	
	public Collection<History> getHistory() {
		return history;
	}

	public void setHistory(Collection<History> history) {
		this.history = history;
	}

	public Collection<FplFixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(Collection<FplFixture> fixtures) {
		this.fixtures = fixtures;
	}

}
