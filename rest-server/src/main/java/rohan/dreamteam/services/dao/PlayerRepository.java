package rohan.dreamteam.services.dao;

import org.springframework.data.repository.CrudRepository;

import rohan.dreamteam.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {
	
	Player findByFplId(Integer fplId);

}
