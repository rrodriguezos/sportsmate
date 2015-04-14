package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Match;
@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
		
	@Query("select t.matches from Tournament t where t.id=?1")
	Collection<Match> findAllMatchesByTournamentId(int tournamentId);
}
