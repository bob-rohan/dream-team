package rohan.dreamteam.domain;

public class PlayerFactory {

	public static Player getPlayer(String shortPosition){
		Player player = null;
		switch (shortPosition){
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
				throw new RuntimeException();
		}
		return player;
	}
}
