package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Tournament;
@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
	
	
	@Query("select c.tournaments from Customer c where c.id=?1")
	Collection<Tournament> findAllTournamentsCreatedByCustomerId(int customerId);
	
	@Query("select u.tournamentsCreated from User u where u.id=?1")
	Collection<Tournament> findAllTournamentsCreatedByUserId(int userId);
	
	@Query("select u.tournaments from User u where u.id=?1")
	Collection<Tournament> findAllTournamentByPrincipal(int userId);

	@Query("select c from Tournament c where c.startMoment > CURRENT_DATE  and c.customer.id=?1 ORDER BY startMoment ASC")
	Collection<Tournament> finAllEventsCalendar(int id);

	
}
