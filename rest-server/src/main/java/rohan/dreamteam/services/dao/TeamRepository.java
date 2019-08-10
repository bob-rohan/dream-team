package rohan.dreamteam.services.dao;

import org.springframework.data.repository.CrudRepository;

import rohan.dreamteam.domain.Team;

public interface TeamRepository extends CrudRepository<Team, String> {

	public Team findByName(String name);
	
	public Team findByFplId(int fplId);
}
