package rohan.dreamteam.fpl.initialData;

public class Element {

	private int id;

	private String web_name;

	private int yellow_cards;

	private int total_points;

	private int team_code;

	private int element_type;

	private int now_cost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeb_name() {
		return web_name;
	}

	public void setWeb_name(String web_name) {
		this.web_name = web_name;
	}

	public int getTotal_points() {
		return total_points;
	}

	public void setTotal_points(int total_points) {
		this.total_points = total_points;
	}

	public int getTeam_code() {
		return team_code;
	}

	public void setTeam_code(int team_code) {
		this.team_code = team_code;
	}

	public int getNow_cost() {
		return now_cost;
	}

	public void setNow_cost(int now_cost) {
		this.now_cost = now_cost;
	}

	public int getElement_type() {
		return element_type;
	}

	public void setElement_type(int element_type) {
		this.element_type = element_type;
	}

	public int getYellow_cards() {
		return yellow_cards;
	}

	public void setYellow_cards(int yellow_cards) {
		this.yellow_cards = yellow_cards;
	}

}
