package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Tournament;
@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
	
	
	@Query("select c.tournaments from Customer c where c.id=?1")
	Collection<Tournament> findAllTournamentsByCustomerId(int customerId);
	
	@Query("select u.tournaments from User u where u.id=?1")
	Collection<Tournament> findAllTournamentsByUserId(int userId);

	
}
