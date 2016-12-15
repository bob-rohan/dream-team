package dreamteam.fpl.playerData;

import java.util.Collection;

public class PlayerDataRoot {

	private Collection<History> history;
	
	public Collection<History> getHistory() {
		return history;
	}

	public void setHistory(Collection<History> history) {
		this.history = history;
	}
}
