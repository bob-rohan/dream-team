package rohan.dreamteam.fpldomain.playerdata;

import java.util.Collection;

public class PlayerDataRoot {

	private Collection<History> history;
	
	private Collection<FplFixture> fixtures_summary;
	
	public Collection<History> getHistory() {
		return history;
	}

	public void setHistory(Collection<History> history) {
		this.history = history;
	}

	public Collection<FplFixture> getFixtures_summary() {
		return fixtures_summary;
	}

	public void setFixtures_summary(Collection<FplFixture> fixtures_summary) {
		this.fixtures_summary = fixtures_summary;
	}

}
