package rohan.dreamteam.services.dao;

import org.springframework.data.repository.CrudRepository;

import rohan.dreamteam.domain.Gameweek;

public interface GameweekRepository extends CrudRepository<Gameweek, String>{

	Gameweek findByGameweek(int gameweek);
	
}
