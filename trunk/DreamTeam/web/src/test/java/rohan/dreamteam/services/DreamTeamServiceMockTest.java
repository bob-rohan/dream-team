package rohan.dreamteam.services;

import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import rohan.dreamteam.domain.Player;

public class DreamTeamServiceMockTest {

	DreamTeamService testSubject = new DreamTeamServiceMock();

	@Before
	public void setUp() {
		// testSubject = mock(DreamTeamService.class);
	}

	@Test
	public void testGetPlayers() {
		Collection<Player> players = testSubject.getPlayers();

		assertFalse(players.isEmpty());
	}

}
