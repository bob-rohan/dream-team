package rohan.dreamteam.domain;

/**
 * TODO: I'm not sure a factory should exist in the domain project.
 * 
 * @author Admin
 *
 */
public class PlayerFactory {

	private PlayerFactory() {
	}

	public static Player getPlayer(String shortPosition) {
		Player player;
		switch (shortPosition) {
		case "GKP":
			player = new Goalkeeper();
			break;
		case "DEF":
			player = new Defender();
			break;
		case "MID":
			player = new Midfielder();
			break;
		case "FWD":
			player = new Forward();
			break;
		default:
			throw new IllegalPlayerException("Unable to get player for type:" + shortPosition);
		}
		return player;
	}
}
