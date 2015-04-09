package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Team;
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

	@Query("select u.teams from User u where u.id=?1")
	Collection<Team> findAllTeamsByUserId(int userId);

	@Query("select t.teams from Tournament t where t.id=?1")
	Collection<Team> findAllTeamsByTournamentId(int tournamentId);
	
}
