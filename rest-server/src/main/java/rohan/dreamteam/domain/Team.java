package rohan.dreamteam.domain;

import java.util.Collection;

import org.springframework.data.annotation.Id;

public class Team {

	@Id
	private String id;

	private int fplId;
	
	private int code;

	private String name;
	
	private String shortName;

	private Collection<Fixture> fixtures;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(Collection<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	public int getFplId() {
		return fplId;
	}

	public void setFplId(int fplId) {
		this.fplId = fplId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


}
